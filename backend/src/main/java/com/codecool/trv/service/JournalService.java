package com.codecool.trv.service;

import com.codecool.trv.dao.JournalDao;
import com.codecool.trv.dao.NoteDao;
import com.codecool.trv.dao.UserDao;
import com.codecool.trv.model.Journal;
import com.codecool.trv.dto.journal.NewJournal;
import com.codecool.trv.model.User;
import com.codecool.trv.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalService {


    private final JournalRepository journalRepository;
    private final UserService userService;
    private final NoteService noteService;

    @Autowired
    public JournalService(JournalRepository journalRepository, UserService userService, NoteService noteService) {
        this.journalRepository = journalRepository;
        this.userService = userService;
        this.noteService = noteService;
    }

    public List<Journal> findAllJournalsByUserId(int userId) {
        //TODO
        return null;
        //return journalDao.findAllJournalsByUserId(userId);
    }

    public Journal findJournalById(int id) {
        //TODO
        return null;
        //return journalDao.findJournalById(id);
    }

    public Journal addNewJournal(NewJournal newJournal) {
        //TODO
        return null;
        //User user = userDao.findUserById(newJournal.getOwnerId());
        //return journalDao.addNewJournal(newJournal, user);
    }

    public List<Journal> deleteAllJournalsByUserId(int id) {
        //TODO
        return null;
        /*List<Journal> journals = findAllJournalsByUserId(id);
        for (Journal journal : journals) {
            deleteAllNotesByJournalId(journal.getId());
        }
        return journalDao.deleteAllJournalsByUserId(id);*/
    }

    public Journal deleteJournalById(int id) {
        //TODO
        return null;
        /*deleteAllNotesByJournalId(id);
        return journalDao.deleteJournalById(id);*/
    }

    private void deleteAllNotesByJournalId(int id) {
        //TODO
        /*Journal journal = journalDao.findJournalById(id);
        noteDao.deleteAllNotesByJournalId(journal.getId());*/
    }

}
