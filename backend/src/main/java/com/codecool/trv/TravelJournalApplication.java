package com.codecool.trv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TravelJournalApplication {
    public static void main(String[] args) {

        SpringApplication.run(TravelJournalApplication.class, args);

    }

}