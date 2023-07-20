package com.codecool.trv;

import com.codecool.trv.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class TravelJournalApplication {
    public static void main(String[] args) {

        SpringApplication.run(TravelJournalApplication.class, args);

    }

}