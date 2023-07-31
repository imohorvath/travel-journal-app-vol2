package com.codecool.trv.controller;

import com.codecool.trv.dto.note.NewNote;
import com.codecool.trv.dto.note.NoteResponse;
import com.codecool.trv.dto.note.UpdateNoteResponse;
import com.codecool.trv.dto.note.UpdateNoteRequest;
import com.codecool.trv.exception.ResourceNotFoundException;
import com.codecool.trv.model.Note;
import com.codecool.trv.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes/{noteId}")
    public NoteResponse findNoteById(@PathVariable Long noteId) {
        return noteService.findNoteResponseById(noteId);
    }

    @PutMapping("/notes/{noteId}")
    public UpdateNoteResponse updateNoteById(@PathVariable Long noteId, @RequestBody UpdateNoteRequest updateNoteRequest) {
        return noteService.updateNoteById(noteId);
    }

    @DeleteMapping("/notes/{noteId}")
    public ResponseEntity<?> deleteNoteById(@PathVariable Long noteId) {
        try {
            noteService.deleteNoteById(noteId);
            return new ResponseEntity(HttpStatus.OK);
        } catch(ResourceNotFoundException exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        } catch(Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/notes/{journalId}")
    public List<Note> findAllNotesByJournalId(@PathVariable int journalId) {
        return noteService.findAllNotesByJournalId(journalId);
    }

    @PostMapping("/notes")
    public Note addNewNoteToJournal(@RequestBody NewNote newNote) {
        return noteService.addNewNoteToJournal(newNote);
    }

    @DeleteMapping("/notes/{journalId}/all")
    public List<Note> deleteAllNotesByJournalId(@PathVariable int journalId) {
        return noteService.deleteAllNotesByJournalId(journalId);
    }

    @DeleteMapping("/notes/{journalId}/{noteId}")
    public Note deleteNoteByIdFromJournalById(@PathVariable int journalId, @PathVariable int noteId) {
        return noteService.deleteNoteByIdFromJournalById(journalId, noteId);
    }

}
