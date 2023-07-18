package com.codecool.trv.dao;

import com.codecool.trv.dto.journal.Journal;
import com.codecool.trv.dto.note.Note;
import com.codecool.trv.dto.user.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NoteDao {

    private final List<Note> notes;
    private static int idCounter = 0;

    public NoteDao() {
        this.notes = new ArrayList<>();
    }

    public List<Note> findAllNotesByJournalId(int id) {
        return notes
                .stream()
                .filter(note -> note.getJournal().getId() == id)
                .sorted(Comparator.comparing(Note::getTimestamp))
                .toList();
    }

    public Note addNewNoteToJournal(String text, Journal journal, User creator) {
        idCounter++;
        Note note = new Note(idCounter, text, null, journal, creator);
        notes.add(note);
        return note;
    }
}
