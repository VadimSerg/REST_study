package com.example.bootstudy.controller;

import com.example.bootstudy.model.User;
import com.example.bootstudy.service.RoleService;
import com.example.bootstudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class RESTController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final RoleService roleService;

    @Autowired
    public RESTController(UserService userService, UserDetailsService userDetailsService, RoleService roleService) {
        this.userService= userService;
        this.userDetailsService = userDetailsService;
        this.roleService = roleService;
    }

//
    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> allUsers = userService.getAll();
        return allUsers;

    }


    @GetMapping("/users/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") Long id) {
         User user = userService.getUserById(id);
         return user;
    }



    @PostMapping("/users")
    public void addNewUser(@RequestBody User user) {


        userService.saveUser(user);

    }












//
////    Code for user's page
    @GetMapping("/user")
    public User showUser(@AuthenticationPrincipal UserDetails logedInUser) {

        User user = (User) userDetailsService.loadUserByUsername(logedInUser.getUsername());

        return  user;

    }



}
