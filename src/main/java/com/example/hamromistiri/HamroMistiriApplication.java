package com.example.hamromistiri;

import com.example.hamromistiri.Service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RequiredArgsConstructor
public class HamroMistiriApplication {
    private final SmsService smsService;

    public static void main(String[] args) {
        SpringApplication.run(HamroMistiriApplication.class, args);
    }

    @Bean
    ApplicationRunner runner(){
        return args -> {
            smsService.sendSms("9845637176","Hello from Hamro Mistiri family");
        };
    }
    }