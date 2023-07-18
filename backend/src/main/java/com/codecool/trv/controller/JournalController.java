package com.codecool.trv.controller;

import com.codecool.trv.dto.Journal;
import com.codecool.trv.dto.user.NewUser;
import com.codecool.trv.dto.user.User;
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

    @GetMapping("/all")
    public List<Journal> findAllJournals() {
        return journalService.findAllJournals();
    }

//    @GetMapping("/{id}")
//    public User findUserById(@PathVariable int id) {
//        return journalService.findUserById(id);
//    }
//
//    @PostMapping("/")
//    public User addNewUser(@RequestBody NewUser newUser) {
//        return journalService.addNewUser(newUser);
//    }
//
//    @DeleteMapping("/")
//    public List<User> deleteAllUsers() {
//        return journalService.deleteAllUsers();
//    }
//

}
