package com.codecool.trv.controller;

import com.codecool.trv.dto.journal.Journal;
import com.codecool.trv.dto.journal.NewJournal;
import com.codecool.trv.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("journal")
public class JournalController {

    private final JournalService journalService;

    @Autowired
    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping("/{userId}/all")
    public List<Journal> findAllJournals(@PathVariable int userId) {
        return journalService.findAllJournalsByUserId(userId);
    }

//    @GetMapping("/{id}")
//    public User findUserById(@PathVariable int id) {
//        return journalService.findUserById(id);
//    }
//
    @PostMapping("/")
    public Journal addNewJournal(@RequestBody NewJournal newJournal) {
        return journalService.addNewJournal(newJournal);
    }
//
//    @DeleteMapping("/")
//    public List<User> deleteAllUsers() {
//        return journalService.deleteAllUsers();
//    }
//

}
