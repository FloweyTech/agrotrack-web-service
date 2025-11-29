package com.floweytech.agrotrack.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Locale;

@EnableJpaAuditing
@SpringBootApplication
public class AgrotrackWebServiceApplication {


    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        SpringApplication.run(AgrotrackWebServiceApplication.class, args);
    }

}
