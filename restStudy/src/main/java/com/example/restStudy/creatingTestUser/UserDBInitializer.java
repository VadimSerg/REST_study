package com.example.restStudy.creatingTestUser;

import com.example.restStudy.model.Role;
import com.example.restStudy.model.User;
import com.example.restStudy.service.RoleService;
import com.example.restStudy.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserDBInitializer {

    private final UserService userService;
    private final RoleService roleService;


    public UserDBInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }





    @PostConstruct
    public void createTestUser()  {

        List<Role> allRolesInDb = roleService.getAllRoles();
        Role roleAdmin = roleService.getRoleByName("ROLE_ADMIN");
        Role roleUser = roleService.getRoleByName("ROLE_USER");
        Set<Role> roleSet = new HashSet<>();

        if (!allRolesInDb.contains(roleUser))   {
            roleService.saveRole(new Role("ROLE_USER"));
        }

         if (!allRolesInDb.contains(roleService.getRoleByName("ROLE_ADMIN")))  {
              roleService.saveRole(new Role("ROLE_ADMIN"));
         }

         roleSet.add(roleUser);
         roleSet.add(roleAdmin);
         User user =  new User("VolodyTest","TestSurname",13,"Testcity", "2222",roleSet);
         userService.saveUser(user);
    }
}
