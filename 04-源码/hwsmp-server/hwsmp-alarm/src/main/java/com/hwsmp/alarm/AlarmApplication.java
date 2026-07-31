package com.hwsmp.alarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 告警引擎服务启动类
 */
@SpringBootApplication(scanBasePackages = {"com.hwsmp"})
public class AlarmApplication {
    public static void main(String[] args) {
        SpringApplication.run(AlarmApplication.class, args);
    }
}
