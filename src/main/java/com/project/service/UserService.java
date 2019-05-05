package com.project.service;

import com.project.model.User;

public interface UserService {

    User getCurrentUser();

    void save(User user);

    void update(User User);

    void updatePassword(User user);

    void updateProfile(User user, User userForm);

    void updateAllValues(User user, User userForm);

    void updateSurnameAndMoney(User user, User userForm);
}
