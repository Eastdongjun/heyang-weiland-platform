package com.hwsmp.gis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * GIS地图服务启动类
 */
@SpringBootApplication(scanBasePackages = {"com.hwsmp"})
public class GisApplication {
    public static void main(String[] args) {
        SpringApplication.run(GisApplication.class, args);
    }
}
