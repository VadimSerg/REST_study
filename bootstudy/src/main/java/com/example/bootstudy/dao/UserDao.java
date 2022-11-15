package com.example.bootstudy.dao;

import com.example.bootstudy.model.User;

import java.util.List;

public interface UserDao {


    void save(User user);
    List<User> getAll();
    User getUserById(Long id);
    User update(User user);
    void deleteById(long id);
    void delete(User user);

    User getUserByName(String name);





}
