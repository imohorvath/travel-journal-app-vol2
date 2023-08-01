package com.codecool.trv.service;

import com.codecool.trv.dao.JournalDao;
import com.codecool.trv.dao.NoteDao;
import com.codecool.trv.model.Journal;
import com.codecool.trv.dto.note.NewNote;
import com.codecool.trv.model.Note;
import com.codecool.trv.model.User;
import com.codecool.trv.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> findAllNotesByJournalId(int id) {
        //TODO
        return null;
        //return noteDao.findAllNotesByJournalId(id);
    }

    public Note addNewNoteToJournal(NewNote newNote) {
        //TODO
        return null;
        /*User creator = userDao.findUserById(newNote.getUserId());
        Journal journal = journalDao.findJournalById(newNote.getJournalId());
        return noteDao.addNewNoteToJournal(newNote.getText(), journal, creator);*/
    }

    public List<Note> deleteAllNotesByJournalId(Long journalId) {
        //TODO
        return null;
        //return noteDao.deleteAllNotesByJournalId(journalId);
    }

    public Note deleteNoteByIdFromJournalById(int journalId, int noteId) {
        //TODO
        return null;
        //return noteDao.deleteNoteByIdFromJournalById(journalId, noteId);
    }
}
