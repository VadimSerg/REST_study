package com.example.restStudy.postconstruct;
import com.example.restStudy.model.User;
import com.example.restStudy.service.RoleService;
import com.example.restStudy.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserDBInitializer {

    private final UserService userService;
    private final RoleService roleService;

    public UserDBInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }





    @PostConstruct
    public void createTestUser() throws Exception {
        String roleNames [] = {"ROLE_USER"};
        User admin =  new User("VolodyTest","TestSurname",13,"Testcity", "2222", roleService.getRolesByRoleNames(roleNames));

        userService.saveUser(admin);
        System.out.println("Тестовый пользователь сохранен. ");

    }
}
