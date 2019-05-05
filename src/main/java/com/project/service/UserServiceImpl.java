package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.model.User;
import com.project.dao.UserRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getCurrentUser() {
        User user = userRepository.findByUsername(getUserName());
        if (user == null) {
            throw new SecurityException();
        }
        return user;
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }
   
    @Override
    public void updateProfile(User user, User userForm) {
        updateAllValues(user, userForm);
    }
    
    @Override
    public User modifyDate() {
        User user = getCurrentUser();
        Date date = user.getDateOfBirth();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        user.setEditDate(dateString);
        return user;
    }
    
    private String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            return auth.getName();
        }
        return "";
    }
     
    private void updateAllValues(User user, User userForm) {
        user.setUsername(userForm.getUsername());
        user.setName(userForm.getName());
        user.setSurname(userForm.getSurname());
        user.setDateOfBirth(userForm.getDateOfBirth());
        user.setGender(userForm.getGender());
        user.setHeight(userForm.getHeight());
        user.setWeight(userForm.getWeight());
        user.setFavourite(userForm.getFavourite());
        update(user);
    }
}
