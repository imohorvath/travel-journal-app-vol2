package com.codecool.trv.service;

import com.codecool.trv.dao.JournalDao;
import com.codecool.trv.dao.UserDao;
import com.codecool.trv.dto.journal.Journal;
import com.codecool.trv.dto.journal.NewJournal;
import com.codecool.trv.dto.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalService {

    private final JournalDao journalDao;
    private final UserDao userDao;
    @Autowired
    public JournalService(JournalDao journalDao, UserDao userDao) {
        this.journalDao = journalDao;
        this.userDao = userDao;
    }

    public List<Journal> findAllJournalsByUserId(int userId) {
        return journalDao.findAllJournalsByUserId(userId);
    }

    public Journal findJournalById(int id) {
        return journalDao.findJournalById(id);
    }

    public Journal addNewJournal(NewJournal newJournal) {
        User user = userDao.findUserById(newJournal.getOwnerId());
        int id = journalDao.addNewJournal(newJournal, user);
        return findJournalById(id);
    }
}
