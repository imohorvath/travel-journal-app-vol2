package com.codecool.trv.controller;

import com.codecool.trv.dto.journal.JournalResponse;
import com.codecool.trv.dto.journal.NewJournalResponse;
import com.codecool.trv.dto.note.NewNoteRequest;
import com.codecool.trv.dto.note.NewNoteResponse;
import com.codecool.trv.dto.user.UserResponse;
import com.codecool.trv.exception.ResourceNotFoundException;
import com.codecool.trv.model.Journal;
import com.codecool.trv.dto.journal.NewJournal;
import com.codecool.trv.model.Note;
import com.codecool.trv.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1")
public class JournalController {

    private final JournalService journalService;

    @Autowired
    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping("/users/{userId}/journals")
    public List<JournalResponse> findAllJournals(@PathVariable Long userId) {
        return journalService.findAllJournalsByUserId(userId);
    }

    @GetMapping("/journals/{journalId}")
    public JournalResponse findJournalById(@PathVariable Long journalId) {
        return journalService.findJournalResponse(journalId);
    }

    @PostMapping("/users/{userId}/journals/")
    public NewJournalResponse addNewJournal(@PathVariable Long userId, @RequestBody NewJournal newJournal) {
        return journalService.addNewJournal(userId, newJournal);
    }

    @DeleteMapping("/journals/{journalId}")
    public ResponseEntity<?> deleteJournalById(@PathVariable Long journalId) {
        try{
            journalService.deleteJournalById(journalId);
        } catch(ResourceNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/journals/{journalId}/notes/")
    public List<Note> findAllNotesByJournalId(@PathVariable Long journalId) {
        return journalService.findAllNotesByJournalId(journalId);
    }

    @PostMapping("/journals/{journalId}/notes/{userId}")
    public NewNoteResponse postNoteToJournalById(@PathVariable Long journalId, @PathVariable Long userId, @RequestBody NewNoteRequest newNoteRequest) {
        return journalService.postNoteToJournalById(journalId, userId, newNoteRequest);
    }

    @DeleteMapping("/journals/{journalId}/notes/")
    public void deleteAllNotesFromJournalById() {
        //TODO
    } 

    @GetMapping("/journals/{journalId}/contributors")
    public Set<UserResponse> getContributorsById(@PathVariable Long journalId) {
        return journalService.getContributorsById(journalId);
    }

    @GetMapping("/journals/contributors/{userId}")
    public List<Journal> findAllJournalsByContributorId(@PathVariable Long userId) {
        return journalService.findAllJournalsByContributorId(userId);
    }

    @DeleteMapping("/journals/{journalId}/contributors/{userId}")
    public UserResponse deleteContributorFromJournal(@PathVariable Long journalId, @PathVariable Long userId) {
        return journalService.deleteContributorFromJournal(journalId, userId);
    }

}
