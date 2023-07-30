package com.codecool.trv.controller;

import com.codecool.trv.dto.user.NewUserRequest;
import com.codecool.trv.model.User;
import com.codecool.trv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping("/")
    public User addNewUser(@RequestBody NewUserRequest newUserRequest) {
        return userService.addNewUser(newUserRequest);
    }

    @DeleteMapping("/")
    public List<User> deleteAllUsers() {
        return userService.deleteAllUsers();
    }

}
