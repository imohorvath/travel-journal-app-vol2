package com.codecool.trv.service;

import com.codecool.trv.dto.user.NewUserRequest;
import com.codecool.trv.dto.user.UpdateUserRequest;
import com.codecool.trv.dto.user.UserResponse;
import com.codecool.trv.exception.ResourceNotFoundException;
import com.codecool.trv.model.User;
import com.codecool.trv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<UserResponse> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> new UserResponse(user.getId(), user.getUsername())).toList();
    }

    public UserResponse findUserResponseById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return new UserResponse(user.getId(), user.getUsername());
    }

    User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public UserResponse addUser(NewUserRequest newUserRequest) {
        //TODO request validation!!! - unique username, unique email
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

    public UserResponse updateUserById(Long id, UpdateUserRequest updateUserRequest) {
        //TODO request validation!!! - unique username, unique email
        //https://reflectoring.io/bean-validation-with-spring-boot/#using-validation-groups-to-validate-objects-differently-for-different-use-cases

        /*Is's better because getOne(ID id) gets you only a reference (proxy) object
         and does not fetch it from the DB. On this reference you can set what you want
         and on save() it will do just an SQL UPDATE statement like you expect it.---maybe not :(
         */
        /*User userToUpdate;
        try {
            userToUpdate = userRepository.getOne(id);
        } catch (Exception exception) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }*/

        User userToUpdate = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        userToUpdate.setUsername(updateUserRequest.username());
        userToUpdate.setFirstName(updateUserRequest.firstName());
        userToUpdate.setLastName(updateUserRequest.lastName());
        userToUpdate.setEmail(updateUserRequest.email());
        userToUpdate.setPassword(updateUserRequest.password());

        userRepository.save(userToUpdate);
        return new UserResponse(userToUpdate.getId(), userToUpdate.getUsername());
    }

    public void deleteUserById(Long id) {
        //TODO maybe not delete the whole entity, just set username to anonymous and set null for all the other fields?
        //TODO do we want to delete all journals and notes owned by the user also?
        // - or just the ones of which he is the owner and there are no contributors.
        // and the notes which where left as contributor will be marked as anonymous...
        userRepository.deleteById(id);
    }

    //FIXME: This is only for testing purposes
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }


    public Set<User> findUsersByIds(Set<Long> userIds) {
        return new HashSet<>(userRepository.findAllById(userIds));
    }
}
