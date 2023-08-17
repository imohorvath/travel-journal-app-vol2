package com.codecool.trv.controller;

import com.codecool.trv.dto.user.NewUserRequest;
import com.codecool.trv.dto.user.UpdateUserRequest;
import com.codecool.trv.dto.user.UserResponse;
import com.codecool.trv.exception.ResourceNotFoundException;
import com.codecool.trv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/")
    public List<UserResponse> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserResponse findUserById(@PathVariable Long id, @CurrentSecurityContext SecurityContext context) {
        return userService.findUserResponseById(id);
        // decide here who is the user, and access based on roles
    }

    @GetMapping("/users/username")
    public UserResponse findUserByName(@RequestParam String name) {
        return userService.findUserResponseByName(name);
    }

    @PostMapping("/users/")
    public UserResponse addUser(@RequestBody NewUserRequest newUserRequest) {
        return userService.addUser(newUserRequest);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Long id, @RequestBody UpdateUserRequest updateUserRequest) {
        UserResponse userResponse = userService.updateUserById(id, updateUserRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

}
