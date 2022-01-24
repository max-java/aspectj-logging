package com.tutrit.quickstart.rightservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication(scanBasePackages = "com.tutrit.quickstart")
public class RightServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RightServiceApplication.class, args);
    }

}
