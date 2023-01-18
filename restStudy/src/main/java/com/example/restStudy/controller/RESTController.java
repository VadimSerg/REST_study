package com.example.restStudy.controller;

import com.example.restStudy.customsExceptions.UserNotFoundException;
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
import java.util.List;


@RestController
@RequestMapping()
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
    @GetMapping("admin/users")

    public ResponseEntity< List<User>> getUsers() {
        final List<User> allUsers = userService.getAll();
        return allUsers!=null && !allUsers.isEmpty() ?
                new ResponseEntity<>(allUsers,HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @GetMapping("admin/roles")
    public ResponseEntity< List<Role>> getRoles() {
        final List <Role> allRoles= roleService.getAllRoles();
        return allRoles!=null && !allRoles.isEmpty() ?
                new ResponseEntity<>(allRoles,HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




    @GetMapping("admin/users/{id}")
    @CrossOrigin
    public ResponseEntity<?>  getUserById(@PathVariable("id") Long id) throws UserNotFoundException {

        User user = (User) userService.getUserById(id);
            return (user==null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>( user , HttpStatus.OK );
    }



    @PostMapping("admin/users")
    public ResponseEntity<?> addNewUser(@RequestBody User user) {

        userService.saveUser(user);
        System.out.println("USER'S SAVED SUCCESSFULLY");
        return new ResponseEntity<>(user,HttpStatus.CREATED);

    }


    @PutMapping("admin/update/users/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable("id") Long id) throws UserNotFoundException {
        if(userService.getUserById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(id);
        userService.update(user);
        System.out.println("USER'S CHANGED SUCCESSUllY");
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("admin/delete/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id ) throws UserNotFoundException {
        User user = (User) userService.getUserById(id);
            if(user == null) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
        userService.deleteUser(user);
        System.out.println("User WAS REMOVED");
        return new ResponseEntity<>(user, HttpStatus.OK);

    }




    @GetMapping("/user/auth")
    public ResponseEntity<?> showUser(@AuthenticationPrincipal UserDetails loggedInUser) {
        User user = (User) userDetailsService.loadUserByUsername(loggedInUser.getUsername());
        return new ResponseEntity<>(user,HttpStatus.OK);
    }


}
