package com.teamarc.proxima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@SpringBootApplication
public class ProximaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProximaApplication.class, args);
    }

}
