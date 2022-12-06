package com.example.restStudy.service;


import com.example.restStudy.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.restStudy.dao.UserDao;


@Service(value="userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User myUser = userDao.getUserByName(username);

       if (myUser==null) {
           throw new UsernameNotFoundException("User " + username + " not found");
       }

       return  myUser;

    }
}
