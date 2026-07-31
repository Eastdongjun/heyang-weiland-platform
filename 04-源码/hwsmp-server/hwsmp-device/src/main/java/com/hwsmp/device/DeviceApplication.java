package com.hwsmp.device;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 设备管理服务启动类
 */
@SpringBootApplication(scanBasePackages = {"com.hwsmp"})
public class DeviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeviceApplication.class, args);
    }
}
