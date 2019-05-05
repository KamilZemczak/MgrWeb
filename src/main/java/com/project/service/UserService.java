package com.project.service;

import com.project.model.User;

public interface UserService {

    User getCurrentUser();
    
    User modifyDate();

    void save(User user);

    void update(User User);

    void updateProfile(User user, User userForm);
}
