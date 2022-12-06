package com.example.restStudy.service;

import com.example.restStudy.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    void saveRole(Role role);

    List<Role> getAllRoles();

    Role getRoleById(long id);

    void  update(Role role);

    void deleteRoleById(long id);

    Role getRoleByName(String role);

    Set<Role> getRolesByRoleNames(String[] roleNames);


    Set<Role> getRolesByIds(Long[] ids);
}
