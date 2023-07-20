package com.codecool.trv.service;

import com.codecool.trv.dao.UserDao;
import com.codecool.trv.dto.user.NewUser;
import com.codecool.trv.dto.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    public User findUserById(int userId) {
        return userDao.findUserById(userId);
    }

    public User addNewUser(NewUser newUser) {
        int id = userDao.addNewUser(newUser);
        return findUserById(id);
    }

    public List<User> deleteAllUsers() {
        return userDao.deleteAllUsers();
    }
}
