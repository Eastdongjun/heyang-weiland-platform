package com.hwsmp.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.hwsmp"})
public class UiotApplication {
    public static void main(String[] args) {
        SpringApplication.run(UiotApplication.class, args);
    }
}
