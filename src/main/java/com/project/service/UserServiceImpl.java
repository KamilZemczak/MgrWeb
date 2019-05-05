package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.model.User;
import com.project.dao.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
    public void updatePassword(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }

    private String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            return auth.getName();
        }
        return "";
    }

    @Override
    public void updateSurnameAndMoney(User user, User userForm) {
        user.setSurname(userForm.getSurname());
        update(user);
    }

    @Override
    public void updateAllValues(User user, User userForm) {
        user.setUsername(userForm.getUsername());
        user.setName(userForm.getName());
        user.setSurname(userForm.getSurname());
        user.setDateOfBirth(userForm.getDateOfBirth());
        user.setHeight(userForm.getHeight());
        user.setWeight(userForm.getWeight());
        update(user);
    }

    @Override
    public void updateProfile(User user, User userForm) {
        updateAllValues(user, userForm);

    }
}
