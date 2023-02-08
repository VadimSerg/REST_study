package com.example.restStudy.dao;

import com.example.restStudy.model.User;

import java.util.List;

public interface UserDao {

    void save(User user);
    List<User> getAll();
    User getUserById(Long id);
    User update(User user);
    void delete(User user);
    User getUserByName(String name);
}
