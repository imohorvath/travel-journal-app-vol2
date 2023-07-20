package com.codecool.trv.service;

import com.codecool.trv.dao.JournalDao;
import com.codecool.trv.dao.NoteDao;
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
    private final NoteDao noteDao;

    @Autowired
    public JournalService(JournalDao journalDao, UserDao userDao, NoteDao noteDao) {
        this.journalDao = journalDao;
        this.userDao = userDao;
        this.noteDao = noteDao;
    }

    public List<Journal> findAllJournalsByUserId(int userId) {
        return journalDao.findAllJournalsByUserId(userId);
    }

    public Journal findJournalById(int id) {
        return journalDao.findJournalById(id);
    }

    public Journal addNewJournal(NewJournal newJournal) {
        User user = userDao.findUserById(newJournal.getOwnerId());
        return journalDao.addNewJournal(newJournal, user);
    }

    public List<Journal> deleteAllJournalsByUserId(int id) {
        List<Journal> journals = findAllJournalsByUserId(id);
        for (Journal journal : journals) {
            deleteAllNotesByJournalId(journal.getId());
        }
        return journalDao.deleteAllJournalsByUserId(id);
    }

    public Journal deleteJournalById(int id) {
        deleteAllNotesByJournalId(id);
        return journalDao.deleteJournalById(id);
    }

    private void deleteAllNotesByJournalId(int id) {
        Journal journal = journalDao.findJournalById(id);
        noteDao.deleteAllNotesByJournalId(journal.getId());
    }

}
