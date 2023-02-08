package com.example.restStudy.service;

import com.example.restStudy.customsExceptions.UserNotFoundException;
import com.example.restStudy.dao.UserDao;
import com.example.restStudy.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service(value="userServiceImpl")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

   public UserServiceImpl(UserDao userDao,PasswordEncoder passwordEncoder) {
       this.userDao = userDao;
       this.passwordEncoder = passwordEncoder;
   }



    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        System.out.println("User with ID:" + user.getId() + " was saved");
    }


    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }


    @Override
    public User getUserById(Long id) throws UserNotFoundException {
        Optional<User> optionalUser = Optional.ofNullable(userDao.getUserById(id));
             return optionalUser.orElseThrow(()-> new UserNotFoundException("User with id = "+ id +  " not found"));
        }


    @Override
    @Transactional
    public User update(User user) {
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userDao.update(user);

    }

    @Override
    @Transactional
    public void deleteUser(User user) {
       userDao.delete(user);
    }

    @Override
    @Transactional
    public User update(Long id) {
        return userDao.update(userDao.getUserById(id));
    }

    @Override
    public User getUserByName(String name) throws UserNotFoundException {
        Optional<User> optionalUser = Optional.ofNullable(userDao.getUserByName(name));
        return optionalUser.orElseThrow(()-> new UserNotFoundException("User with id = "+ name +  " not found"));
    }


}