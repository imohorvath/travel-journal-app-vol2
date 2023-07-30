package com.codecool.trv.service;

import com.codecool.trv.dto.user.NewUserRequest;
import com.codecool.trv.dto.user.UserResponse;
import com.codecool.trv.exception.ResourceNotFoundException;
import com.codecool.trv.model.User;
import com.codecool.trv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> new UserResponse(user.getId(), user.getUsername())).toList();
    }

    public UserResponse findUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with id: " + userId));
        return new UserResponse(user.getId(), user.getUsername());
    }

    public User addNewUser(NewUserRequest newUserRequest) {
        //TODO
        return null;
        /*int id = userDao.addNewUser(newUser);
        return findUserById(id);*/
    }

    public List<User> deleteAllUsers() {
        //TODO
        return null;
        //return userDao.deleteAllUsers();
    }
}
