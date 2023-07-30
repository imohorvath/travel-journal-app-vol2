package com.codecool.trv.service;

import com.codecool.trv.dao.UserDao;
import com.codecool.trv.dto.user.NewUser;
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

    public List<User> findAllUsers() {
        //TODO
        return null;
        //return userDao.findAllUsers();
    }

    public User findUserById(int userId) {
        //TODO
        return null;
        //return userDao.findUserById(userId);
    }

    public User addNewUser(NewUser newUser) {
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
