package com.codecool.trv.service;

import com.codecool.trv.dto.user.NewUserRequest;
import com.codecool.trv.dto.user.UpdateUserRequest;
import com.codecool.trv.dto.user.UserResponse;
import com.codecool.trv.exception.ResourceNotFoundException;
import com.codecool.trv.mapper.UserMapper;
import com.codecool.trv.model.Note;
import com.codecool.trv.model.User;
import com.codecool.trv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    Set<User> findUsersByIds(Set<Long> userIds) {
        return new HashSet<>(userRepository.findAllById(userIds));
    }

    public List<UserResponse> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserResponse).toList();
    }

    public UserResponse findUserResponseById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return UserMapper.mapToUserResponse(user);
    }

    public UserResponse findUserResponseByName(String name) {
        User user = userRepository.findUserByUsername(name);
        if(user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        return UserMapper.mapToUserResponse(user);
    }

    public UserResponse addUser(NewUserRequest newUserRequest) {
        //TODO request validation!!! - unique username, unique email
        User user = userRepository.save(UserMapper.mapToUser(newUserRequest));
        return UserMapper.mapToUserResponse(user);
    }

    public UserResponse updateUserById(Long id, UpdateUserRequest updateUserRequest) {
        //TODO request validation!!! - unique username, unique email

        User userToUpdate = findUserById(id);

        userToUpdate.setUsername(updateUserRequest.username());
        userToUpdate.setFirstName(updateUserRequest.firstName());
        userToUpdate.setLastName(updateUserRequest.lastName());
        userToUpdate.setEmail(updateUserRequest.email());
        userToUpdate.setPassword(updateUserRequest.password());

        User user = userRepository.save(userToUpdate);
        return UserMapper.mapToUserResponse(user);
    }

    public void deleteUserById(Long id) throws EmptyResultDataAccessException {
        //FIXME handle delete invalid user
        userRepository.deleteById(id);
    }

}
