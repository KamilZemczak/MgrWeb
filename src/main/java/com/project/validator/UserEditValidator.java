package com.project.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

import com.project.model.User;
import com.project.searcher.UserSearcher;
import com.project.service.UserService;

@Component
public class UserEditValidator implements Validator {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSearcher userSearcher;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (!userService.getCurrentUser().getUsername().equals(user.getUsername()) && userSearcher.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (user.getName().length() < 3 || user.getName().length() > 26) {
            errors.rejectValue("name", "Length.userForm.name");
        }
        if (!user.getName().isEmpty() && !Character.isUpperCase(user.getName().charAt(0))) {
            errors.rejectValue("name", "Letter.userForm.name");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "NotEmpty");
        if (user.getSurname().length() < 3 || user.getName().length() > 26) {
            errors.rejectValue("surname", "Length.userForm.surname");
        }
        if (!user.getSurname().isEmpty() && !Character.isUpperCase(user.getSurname().charAt(0))) {
            errors.rejectValue("surname", "Letter.userForm.surname");
        }

    }
}
