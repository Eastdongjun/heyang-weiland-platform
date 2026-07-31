package main

import (
	"context"
	"fmt"
	"log"
	"net/http"
	"os"
	"os/signal"
	"syscall"
	"time"

	"github.com/gin-gonic/gin"
)

// 视频网关服务 - 负责RTSP拉流、SRS推流、WebRTC网关
func main() {
	gin.SetMode(gin.ReleaseMode)
	r := gin.Default()

	// 健康检查
	r.GET("/health", func(c *gin.Context) {
		c.JSON(200, gin.H{"status": "ok", "service": "video-gateway"})
	})

	// 设备列表 API
	r.GET("/api/v1/video/devices", func(c *gin.Context) {
		c.JSON(200, gin.H{"code": 200, "data": []gin.H{}})
	})

	// 获取视频流地址
	r.GET("/api/v1/video/stream/:deviceId", func(c *gin.Context) {
		deviceID := c.Param("deviceId")
		// TODO: 从SRS获取实际流地址
		c.JSON(200, gin.H{
			"code": 200,
			"data": gin.H{
				"deviceId": deviceID,
				"flvUrl":   fmt.Sprintf("http://localhost:8080/live/%s.flv", deviceID),
				"hlsUrl":   fmt.Sprintf("http://localhost:8080/live/%s.m3u8", deviceID),
				"webrtcUrl": fmt.Sprintf("webrtc://localhost:1985/rtc/v1/whip/?app=live&stream=%s", deviceID),
			},
		})
	})

	// PTZ 云台控制
	r.POST("/api/v1/video/ptz/:deviceId", func(c *gin.Context) {
		deviceID := c.Param("deviceId")
		var req struct {
			Action string `json:"action"` // up/down/left/right/zoomIn/zoomOut/stop
			Speed  int    `json:"speed"`  // 1-10
		}
		if err := c.ShouldBindJSON(&req); err != nil {
			c.JSON(400, gin.H{"code": 400, "message": err.Error()})
			return
		}
		// TODO: 通过ONVIF/GB28181控制云台
		log.Printf("[PTZ] device=%s action=%s speed=%d", deviceID, req.Action, req.Speed)
		c.JSON(200, gin.H{"code": 200, "message": "ok"})
	})

	srv := &http.Server{Addr: ":8081", Handler: r}

	go func() {
		log.Println("🚀 视频网关启动 :8081")
		if err := srv.ListenAndServe(); err != nil && err != http.ErrServerClosed {
			log.Fatalf("启动失败: %v", err)
		}
	}()

	// 优雅关闭
	quit := make(chan os.Signal, 1)
	signal.Notify(quit, syscall.SIGINT, syscall.SIGTERM)
	<-quit
	log.Println("正在关闭服务...")

	ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
	defer cancel()
	srv.Shutdown(ctx)
	log.Println("服务已关闭")
}
