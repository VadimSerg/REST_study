package com.example.bootstudy.service;

import com.zaxxer.hikari.HikariConfig;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.bootstudy.dao.UserDao;
import com.example.bootstudy.model.User;

import java.util.List;

@Service(value="userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

   public UserServiceImpl(UserDao userDao,PasswordEncoder passwordEncoder) {
       this.userDao = userDao;
       this.passwordEncoder = passwordEncoder;
   }



    @Override
    public void saveUser(User user) {



      //  Set<Role> updatedRoles = new HashSet<>((Collection<? extends Role>) user.getAuthorities());
     // user.setRoles(user.getRoles());

        System.out.println("************SAVING PROCESS********************************");
        user.setPassword(passwordEncoder.encode(user.getPassword()));




        userDao.save(user);

        System.out.println("UserID:" + user.getId() +
                "with rolename " +
                //user.getRoles().toArray().toString()+
                " was saved");///ВЫВОД ---id user присваивается в дб после
        // сохранения , но никак не до сохранения

    }


    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }


    @Override
    public User getUserById(Long id) {
        return  userDao.getUserById(id);
    }


    @Override
    public User update(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userDao.update(user);

    }


    @Override
    public void deleteUserById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public void deleteUser(User user) {
       userDao.delete(user);
    }

    @Override
    public User update(Long id) {
        return userDao.update(userDao.getUserById(id));
    }


}