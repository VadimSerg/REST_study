package com.example.bootstudy.service;

import com.example.bootstudy.model.User;

import java.util.List;

public interface UserService   {
//        extends UserDetailsService


    void saveUser(User user);

    List<User> getAll();

    User getUserById(Long id);

    User update(User user);

    void deleteUserById(Long id);
    void deleteUser(User user);

    User update(Long id);


}