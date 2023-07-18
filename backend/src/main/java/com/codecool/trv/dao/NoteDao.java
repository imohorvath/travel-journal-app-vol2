package com.codecool.trv.dao;

import com.codecool.trv.dto.note.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteDao {

    private final List<Note> notes;

    public NoteDao() {
        this.notes = new ArrayList<>();
    }

    public List<Note> findAllNotesInJournalById(int id) {
        //return notes.stream().filter(note -> note.getJournal().getId() == id).toList();
        return notes;
    }


}
