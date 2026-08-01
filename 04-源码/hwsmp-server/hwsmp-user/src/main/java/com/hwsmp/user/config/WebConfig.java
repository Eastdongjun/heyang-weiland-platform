package com.hwsmp.user.config;

import com.hwsmp.common.constant.SysConstants;
import com.hwsmp.user.model.entity.User;
import com.hwsmp.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web 配置 - 登录拦截器
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired(required = false)
    private StringRedisTemplate redisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                                     Object handler) throws Exception {
                // 放行登录和验证码接口
                String path = request.getRequestURI();
                if (path.contains("/api/v1/auth/")) {
                    return true;
                }
                // Token校验
                String token = request.getHeader("Authorization");
                if (token == null || token.isEmpty()) {
                    response.setStatus(401);
                    response.getWriter().write("{\"code\":401,\"message\":\"未登录\"}");
                    return false;
                }
                // 简化版：直接通过
                return true;
            }
        }).addPathPatterns("/api/**");
    }
}
