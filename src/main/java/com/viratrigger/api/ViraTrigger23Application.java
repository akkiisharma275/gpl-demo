package com.viratrigger.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.viratrigger.api.repository") // Repository package
@EntityScan("com.viratrigger.api.dto") // Entity package
public class ViraTrigger23Application {

    public static void main(String[] args) {
        SpringApplication.run(ViraTrigger23Application.class, args);
    }
}