package com.example.restStudy.service;

import com.example.restStudy.dao.RoleDao;
import com.example.restStudy.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value="roleServiceImpl")
@Transactional(readOnly = true)
public class RoleServiceImpl  implements  RoleService{

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {

        this.roleDao = roleDao;
    }


    @Override
    @Transactional
    public void saveRole(Role role) {

        roleDao.save(role);

    }

    @Override
    public List<Role> getAllRoles() {

        return roleDao.getAllRoles();
    }

    @Override
    @Transactional
    public void update(Role role) {

        roleDao.update(role);
    }



    @Override
    public Role getRoleByName(String role) {

        return roleDao.getAuthorityByName(role);
    }

}