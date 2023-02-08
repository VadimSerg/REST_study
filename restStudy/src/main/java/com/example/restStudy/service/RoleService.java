package com.example.restStudy.service;

import com.example.restStudy.model.Role;

import java.util.List;


public interface RoleService {

    void saveRole(Role role);
    List<Role> getAllRoles();
    void  update(Role role);
    Role getRoleByName(String role);

}
