package com.example.restStudy.controller;

import com.example.restStudy.model.Role;
import com.example.restStudy.model.User;
import com.example.restStudy.service.RoleService;
import com.example.restStudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
//@CrossOrigin(origins = "http://localhost:8060")
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

    @CrossOrigin
    @GetMapping("/users")

    public ResponseEntity< List<User>> getUsers() {
       // List<Role> rolesList = roleService.getAllRoles();
        final List<User> allUsers = userService.getAll();
        return allUsers!=null && !allUsers.isEmpty() ?
                new ResponseEntity<>(allUsers,HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

//    @GetMapping("/users")
//    public ModelAndView getUsers(ModelAndView modelAndView ) {
//        List<Role> rolesList = roleService.getAllRoles();
//        modelAndView.setViewName("./listBS");
//        modelAndView.addObject("roleSet",rolesList);
//
//        return modelAndView;
//    }





    @GetMapping("/users/{id}")
  //  @CrossOrigin
    public ResponseEntity<?>  getUserById(@RequestBody User user,@PathVariable("id") Long id) {

        user = userService.getUserById(id);
        if (user==null) {
            System.out.println("USER'S NOT FOUND");
        }
            return (user==null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>( user , HttpStatus.OK );

    }



    @PostMapping("/users")
    public ResponseEntity<?> addNewUser(@RequestBody User user) {

        userService.saveUser(user);
        System.out.println("USER'S SAVED SUCCESSFULLY");
        return new ResponseEntity<>(user,HttpStatus.CREATED);

    }


    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable("id") Long id) {




        if(userService.getUserById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(id);
        userService.update(user);
        System.out.println("USER'S CHANGED SUCCESSUllY");
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@RequestBody User user, @PathVariable("id") Long id ) {
        user = userService.getUserById(id);
            if(user == null) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
        userService.deleteUser(user);
        System.out.println("User WAS REMOVED");
        return new ResponseEntity<>(user, HttpStatus.OK);

    }











//
////    Code for user's page

    
    @GetMapping("/user")
    public User showUser(@AuthenticationPrincipal UserDetails logedInUser) {

        User user = (User) userDetailsService.loadUserByUsername(logedInUser.getUsername());

        return  user;

    }





}
