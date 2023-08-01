package com.codecool.trv.controller;

import com.codecool.trv.dto.note.NewNote;
import com.codecool.trv.model.Note;
import com.codecool.trv.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/notes")
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
    public List<Note> deleteAllNotesByJournalId(@PathVariable Long journalId) {
        return noteService.deleteAllNotesByJournalId(journalId);
    }

    @DeleteMapping("/{journalId}/{noteId}")
    public Note deleteNoteByIdFromJournalById(@PathVariable int journalId, @PathVariable int noteId) {
        return noteService.deleteNoteByIdFromJournalById(journalId, noteId);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleElementNotFound(NoSuchElementException exception) {
        return ResponseEntity.notFound().build();
    }

}
