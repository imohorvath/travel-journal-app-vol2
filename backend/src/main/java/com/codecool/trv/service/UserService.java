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

    public UserResponse addNewUser(NewUserRequest newUserRequest) {
        //TODO validation!!! - unique username, unique email
        User userToSave = User
                .builder()
                .username(newUserRequest.username())
                .firstName(newUserRequest.firstName())
                .lastName(newUserRequest.lastName())
                .email(newUserRequest.email())
                .password(newUserRequest.password())
                .build();

        User user = userRepository.save(userToSave);
        return new UserResponse(user.getId(), user.getUsername());
    }

    public List<User> deleteAllUsers() {
        //TODO
        return null;
        //return userDao.deleteAllUsers();
    }
}
