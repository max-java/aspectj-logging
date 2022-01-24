package com.tutrit.quickstart.leftservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication(scanBasePackages = "com.tutrit.quickstart" )
public class LeftServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeftServiceApplication.class, args);
    }

}
