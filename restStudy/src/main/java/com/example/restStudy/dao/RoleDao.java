package com.example.restStudy.dao;

import com.example.restStudy.model.Role;

import java.util.List;

public interface RoleDao  {

    void save(Role role);
    List<Role> getAllRoles();
    Role getRoleById(long id);
    void  update(Role role);
    Role getAuthorityByName(String name);


}
