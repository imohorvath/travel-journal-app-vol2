package com.codecool.trv.service;

import com.codecool.trv.dao.JournalDao;
import com.codecool.trv.dao.NoteDao;
import com.codecool.trv.dao.UserDao;
import com.codecool.trv.dto.note.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteDao noteDao;
    private final JournalDao journalDao;
    private final UserDao userDao;

    public NoteService(NoteDao noteDao, JournalDao journalDao, UserDao userDao) {
        this.noteDao = noteDao;
        this.journalDao = journalDao;
        this.userDao = userDao;
    }

    public List<Note> findAllNotesInJournalById(int id) {
        return noteDao.findAllNotesInJournalById(id);
    }


    public Note addNewNoteToJournal() {
        return null;
    }
}
