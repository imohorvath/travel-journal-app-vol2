package com.codecool.trv.service;

import com.codecool.trv.dao.JournalDao;
import com.codecool.trv.dao.NoteDao;
import com.codecool.trv.dao.UserDao;
import com.codecool.trv.dto.journal.Journal;
import com.codecool.trv.dto.note.NewNote;
import com.codecool.trv.dto.note.Note;
import com.codecool.trv.dto.user.User;
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

    public List<Note> findAllNotesByJournalId(int id) {
        return noteDao.findAllNotesByJournalId(id);
    }

    public Note addNewNoteToJournal(NewNote newNote) {
        User creator = userDao.findUserById(newNote.getUserId());
        Journal journal = journalDao.findJournalById(newNote.getJournalId());
        return noteDao.addNewNoteToJournal(newNote.getText(), journal, creator);
    }

    public List<Note> deleteAllNotesByJournalId(int journalId) {
        return noteDao.deleteAllNotesByJournalId(journalId);
    }

    public Note deleteNoteByIdFromJournalById(int journalId, int noteId) {
        return noteDao.deleteNoteByIdFromJournalById(journalId, noteId);
    }
}
