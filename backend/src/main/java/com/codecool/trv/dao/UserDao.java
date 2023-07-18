package com.codecool.trv.dao;

import com.codecool.trv.dto.user.NewUser;
import com.codecool.trv.dto.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserDao {

    private final List<User> users;

    private static int idCounter = 0;

    public UserDao() {
        this.users = new ArrayList<>();
    }

    public List<User> findAllUsers() {
        return users;
    }

    public User findUserById(int userId) {
        return users.stream().filter(user -> user.getId() == userId).findFirst().orElseThrow(() -> new NoSuchElementException("No user found."));
    }

    public int addNewUser(NewUser newUser) {
        idCounter++;
        User user = new User(
                idCounter,
                newUser.getNickName(),
                newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getEmail(),
                newUser.getPassword()
        );
        users.add(user);
        return idCounter;
    }

    public List<User> deleteAllUsers() {
        users.clear();
        return users;
    }

}
