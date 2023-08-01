package com.codecool.trv.controller;

import com.codecool.trv.dto.user.NewUserRequest;
import com.codecool.trv.dto.user.UpdateUserRequest;
import com.codecool.trv.dto.user.UserResponse;
import com.codecool.trv.exception.ResourceNotFoundException;
import com.codecool.trv.model.User;
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

    //FIXME: This might be unnecessary, or admin privilage --- or return UserResponse instead at least
    @GetMapping("/users/")
    public List<UserResponse> findAllUsers() {
        return userService.findAllUsers();
    }

    //FIXME: This might be unnecessary, in userservice is enough to have a findUserById() --- or return UserResponse instead
    @GetMapping("/users/{id}")
    public UserResponse findUserById(@PathVariable Long id) {
        return userService.findUserResponseById(id);
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

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    //FIXME: This is only for testing purposes
    @DeleteMapping("/users")
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

}
