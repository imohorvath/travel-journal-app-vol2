package com.codecool.trv.controller;

import com.codecool.trv.dto.user.NewUserRequest;
import com.codecool.trv.dto.user.UpdateUserRequest;
import com.codecool.trv.dto.user.UserResponse;
import com.codecool.trv.exception.ResourceNotFoundException;
import com.codecool.trv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public UserResponse findUserById(@PathVariable Long id) {
        return userService.findUserResponseById(id);
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
        UserResponse userResponse;
        try {
            userResponse = userService.updateUserById(id, updateUserRequest);
        } catch(ResourceNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}/soft")
    public void deleteUserByIdSoft(@PathVariable Long id) {
        //FIXME check cascadeType
        //TODO
        userService.deleteUserById(id);
    }

    @DeleteMapping("/users/{id}/hard")
    public void deleteUserByIdHard(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

}
