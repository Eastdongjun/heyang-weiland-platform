package com.hwsmp.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.hwsmp"})
public class UnotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(UnotificationApplication.class, args);
    }
}
