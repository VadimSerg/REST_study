package com.example.restStudy.service;

import com.example.restStudy.dao.UserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service(value="userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return Optional.ofNullable(userDao.getUserByName(username)).orElseThrow(()->
                new UsernameNotFoundException("User " + username + " not found"));
    }
}
