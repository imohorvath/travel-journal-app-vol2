package com.codecool.trv.controller;

import com.codecool.trv.dto.journal.JournalResponse;
import com.codecool.trv.dto.journal.NewJournalResponse;
import com.codecool.trv.dto.note.NewNoteResponse;
import com.codecool.trv.dto.note.NoteResponse;
import com.codecool.trv.dto.user.UserResponse;
import com.codecool.trv.dto.journal.NewJournal;
import com.codecool.trv.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
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

    @DeleteMapping("/users/{id}/soft")
    public void deleteUserByIdSoft(@PathVariable Long id) {
        journalService.deleteUserByIdSoft(id);
    }

    @DeleteMapping("/users/{id}/hard")
    public void deleteUserByIdHard(@PathVariable Long id) {
        journalService.deleteUserByIdHard(id);
    }

    @DeleteMapping("/journals/{journalId}")
    public void deleteJournalById(@PathVariable Long journalId) {
        journalService.deleteJournalById(journalId);
    }

    @GetMapping("/journals/{journalId}/notes/")
    public List<NoteResponse> findAllNotesByJournalId(@PathVariable Long journalId) {
        return journalService.findAllNotesByJournalId(journalId);
    }

    /*@PostMapping("/journals/{journalId}/notes/{userId}")
    public NewNoteResponse postNoteToJournalById(@PathVariable Long journalId, @PathVariable Long userId, @RequestBody NewNoteRequest newNoteRequest) {
        return journalService.postNoteToJournalById(journalId, userId, newNoteRequest);
    }*/

    @PostMapping(value = "/journals/{journalId}/notes/{userId}/image",
                consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public NewNoteResponse postNoteToJournalById(@PathVariable Long journalId, @PathVariable Long userId, @RequestParam String text, @RequestParam MultipartFile[] files) {
        return journalService.postNoteToJournalById(journalId, userId, text, files);
    }

    @DeleteMapping("/journals/{journalId}/notes/")
    public void deleteAllNotesFromJournalById(@PathVariable Long journalId) {
        journalService.deleteAllNotesByJournalId(journalId);
    } 

    @GetMapping("/journals/{journalId}/contributors")
    public Set<UserResponse> getContributorsById(@PathVariable Long journalId) {
        return journalService.getContributorsById(journalId);
    }

    @GetMapping("/journals/contributors/{userId}")
    public List<JournalResponse> findAllJournalsByContributorId(@PathVariable Long userId) {
        return journalService.findAllJournalsByContributorId(userId);
    }

    @DeleteMapping("/journals/{journalId}/contributors/{userId}")
    public void deleteContributorFromJournal(@PathVariable Long journalId, @PathVariable Long userId) {
        journalService.deleteContributorFromJournal(journalId, userId);
    }

}
