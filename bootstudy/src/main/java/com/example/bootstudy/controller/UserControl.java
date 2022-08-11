package com.example.bootstudy.controller;


import com.example.bootstudy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserControl {

    private final UserDetailsService userDetailsService;


    @Autowired
    public UserControl(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @GetMapping("/user")
    public String showUserPage(Model model,
                               @AuthenticationPrincipal UserDetails logedInUser) {

        User user = (User) userDetailsService.loadUserByUsername(logedInUser.getUsername());
        model.addAttribute("loggedInUser",user);
        return  "userPage";

    }
}
