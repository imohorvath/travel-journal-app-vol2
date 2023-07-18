package com.codecool.trv.controller;

import com.codecool.trv.dto.user.NewUser;
import com.codecool.trv.dto.user.User;
import com.codecool.trv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable int id) {
        return userService.findUserById(id);
    }

    @PostMapping("/")
    public User addNewUser(@RequestBody NewUser newUser) {
        return userService.addNewUser(newUser);
    }

    @DeleteMapping("/")
    public List<User> deleteAllUsers() {
        return userService.deleteAllUsers();
    }

}
