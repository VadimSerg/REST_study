package com.example.restStudy.service;

import com.example.restStudy.customsExceptions.UserNotFoundException;
import com.example.restStudy.model.User;

import java.util.List;

public interface  UserService   {

    void saveUser(User user);
    List<User> getAll();
    User getUserById(Long id) throws UserNotFoundException;
    User update(User user);
    void deleteUser(User user);
    User update(Long id);
    User getUserByName(String name) throws UserNotFoundException;
}