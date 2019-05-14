package com.project.service;

import com.project.model.User;
import java.util.Date;

public interface UserService {

    User getCurrentUser();
    
    User modifyDate();

    void save(User user);

    void update(User User);

    void updateProfile(User user, User userForm);
    
     void setDetails(User userToSet, Integer id, String name, String surname, String username, Date dateOfBirth, String gender, Integer weight, Integer height, String favourite);
}
