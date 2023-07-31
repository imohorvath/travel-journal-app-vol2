package com.codecool.trv.controller;

import com.codecool.trv.dto.journal.NewJournalResponse;
import com.codecool.trv.model.Journal;
import com.codecool.trv.dto.journal.NewJournal;
import com.codecool.trv.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Journal findJournalById(@PathVariable int journalId) {
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


}
