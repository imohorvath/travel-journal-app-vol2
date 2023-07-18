package com.codecool.trv.config;

import com.codecool.trv.dao.JournalDao;
import com.codecool.trv.dao.NoteDao;
import com.codecool.trv.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public UserDao getUserDao() {
        return new UserDao();
    }

    @Bean
    public JournalDao getJournalDao() {
        return new JournalDao();
    }

    @Bean
    public NoteDao getNoteDao() {
        return new NoteDao();
    }


}
