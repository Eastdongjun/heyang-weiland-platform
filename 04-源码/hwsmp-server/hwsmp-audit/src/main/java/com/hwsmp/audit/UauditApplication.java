package com.hwsmp.audit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.hwsmp"})
public class UauditApplication {
    public static void main(String[] args) {
        SpringApplication.run(UauditApplication.class, args);
    }
}
