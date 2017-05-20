package org.neda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NedaBackend {
    public static void main(String[] args) throws Exception {
        System.setProperty("spring.config.name", "neda");
        SpringApplication.run(NedaBackend.class);

    }
}