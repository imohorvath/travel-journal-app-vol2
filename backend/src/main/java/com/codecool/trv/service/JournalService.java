package com.codecool.trv.service;

import com.codecool.trv.dao.JournalDao;
import com.codecool.trv.dto.Journal;
import com.codecool.trv.dto.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalService {

    private JournalDao journalDao;

    @Autowired
    public JournalService(JournalDao journalDao) {
        this.journalDao = journalDao;
    }

    public List<Journal> findAllJournals() {
        return journalDao.findAllJournals();
    }
}
