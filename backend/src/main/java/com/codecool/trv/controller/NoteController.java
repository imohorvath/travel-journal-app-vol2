package com.codecool.trv.controller;

import com.codecool.trv.dto.note.NewNoteRequest;
import com.codecool.trv.dto.note.NoteResponse;
import com.codecool.trv.dto.note.UpdateNoteResponse;
import com.codecool.trv.dto.note.UpdateNoteRequest;
import com.codecool.trv.model.Note;
import com.codecool.trv.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void deleteNoteById(@PathVariable Long noteId) {
        noteService.deleteNoteById(noteId);
    }

}
