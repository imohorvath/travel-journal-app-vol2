package com.codecool.trv.controller;

import com.codecool.trv.dto.note.NewNote;
import com.codecool.trv.dto.note.Note;
import com.codecool.trv.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/note")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{journalId}/all")
    public List<Note> findAllNotesByJournalId(@PathVariable int journalId) {
        return noteService.findAllNotesByJournalId(journalId);
    }

    @PostMapping("/")
    public Note addNewNoteToJournal(@RequestBody NewNote newNote) {
        return noteService.addNewNoteToJournal(newNote);
    }

    @DeleteMapping("/{journalId}/all")
    public List<Note> deleteAllNotesByJournalId(@PathVariable int journalId) {
        return noteService.deleteAllNotesByJournalId(journalId);
    }

    @DeleteMapping("/{journalId}/{noteId}")
    public Note deleteNoteByIdFromJournalById(@PathVariable int journalId, @PathVariable int noteId) {
        return noteService.deleteNoteByIdFromJournalById(journalId, noteId);
    }

}
