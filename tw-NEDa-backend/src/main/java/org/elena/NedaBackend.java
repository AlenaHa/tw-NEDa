package org.elena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.elena"})
public class NedaBackend {
    public static void main(String[] args) throws Exception {
        System.setProperty("spring.config.name", "neda");
        SpringApplication.run(NedaBackend.class);
    }
}