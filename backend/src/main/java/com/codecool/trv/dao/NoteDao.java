package com.codecool.trv.dao;

import com.codecool.trv.dto.journal.Journal;
import com.codecool.trv.dto.note.Note;
import com.codecool.trv.dto.user.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class NoteDao {

    private final List<Note> notes;

    public NoteDao() {
        this.notes = new ArrayList<>();
    }

    public List<Note> findAllNotesByJournalId(int id) {
        return notes
                .stream()
                .filter(note -> note.getJournal().getId() == id)
                .sorted(Comparator.comparing(Note::getTimestamp).reversed())
                .toList();
    }

    public Note addNewNoteToJournal(String text, Journal journal, User creator) {
        int numberOfNotes = findAllNotesByJournalId(journal.getId()).size();
        numberOfNotes++;
        Note note = new Note(numberOfNotes, text, null, journal, creator);
        notes.add(note);
        return note;
    }

    public List<Note> deleteAllNotesByJournalId(int journalId) {
        List<Note> deletedNotes = notes
                .stream()
                .filter(note -> note.getJournal().getId() == journalId)
                .toList();
        notes.removeAll(deletedNotes);
        return deletedNotes;
    }

    public Note deleteNoteByIdFromJournalById(int journalId, int noteId) {
        Note deletedNote = notes
                .stream()
                .filter(note -> note.getJournal().getId() == journalId)
                .filter(note -> note.getId() == noteId)
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException());
        return deletedNote;
    }
}
