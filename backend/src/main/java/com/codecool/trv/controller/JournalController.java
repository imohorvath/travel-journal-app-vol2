package com.codecool.trv.controller;

import com.codecool.trv.dto.journal.NewJournalResponse;
import com.codecool.trv.dto.user.UserResponse;
import com.codecool.trv.model.Journal;
import com.codecool.trv.dto.journal.NewJournal;
import com.codecool.trv.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/api/v1/users/{userId}/journals")
    public List<Journal> findAllJournals(@PathVariable Long userId) {
        return journalService.findAllJournalsByUserId(userId);
    }

    @GetMapping("/journals/{journalId}")
    public Journal findJournalById(@PathVariable Long journalId) {
        return journalService.findJournalById(journalId);
    }

    @PostMapping("/users/{userId}/journals/")
    public NewJournalResponse addNewJournal(@PathVariable Long userId, @RequestBody NewJournal newJournal) {
        return journalService.addNewJournal(userId, newJournal);
    }

    @DeleteMapping("/journals/{userId}/all")
    public List<Journal> deleteAllJournalsByUserId(@PathVariable int userId) {
        return journalService.deleteAllJournalsByUserId(userId);
    }

    @DeleteMapping("/journals/{journalId}")
    public Journal deleteJournalById(@PathVariable int journalId) {
        return journalService.deleteJournalById(journalId);
    }

    @GetMapping("/journals/{journalId}/contributors")
    public Set<UserResponse> getContributorsById(@PathVariable Long journalId) {
        return journalService.getContributorsById(journalId);
    }

    @PutMapping("/journals/contributors/{userId}")
    public List<Journal> findAllJournalsByContributorId(@PathVariable Long userId) {
        return journalService.findAllJournalsByContributorId(userId);
    }

    @DeleteMapping("/journals/{journalId}/contributors/{userId}")
    public UserResponse deleteContributorFromJournal(@PathVariable Long journalId, @PathVariable Long userId) {
        return journalService.deleteContributorFromJournal(journalId, userId);
    }

}
