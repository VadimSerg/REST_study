package com.example.restStudy.dao;

import com.example.restStudy.model.Role;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Repository
public class RoleDaoImpl  implements RoleDao  {

    @PersistenceContext
    private EntityManager entityManager;


    public void save(Role role) {
        entityManager.persist(role);

    }

    public List <Role> getAllRoles() {

        return   entityManager.createQuery("select r  from  Role r ",Role.class).getResultList();

    }


    public Role getRoleById(long id) {

        return  entityManager.find(Role.class, id);
    }

    public void  update(Role role) {

        entityManager.merge(role);
    }


    public void deleteById(long id) {

        entityManager.remove(getRoleById(id));
    }

    public Role getAuthorityByName(String name) {
        return  entityManager.
                createQuery("select r from Role r where r.roleName =:role" ,Role.class).
                setParameter("role",name).getSingleResult();
    }






}
