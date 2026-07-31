package com.hwsmp.drone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.hwsmp"})
public class UdroneApplication {
    public static void main(String[] args) {
        SpringApplication.run(UdroneApplication.class, args);
    }
}
