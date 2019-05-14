package com.project.controller;

import com.project.dao.UserRepository;
import com.project.model.User;
import com.project.service.SecurityService;
import com.project.service.UserDetailsServiceImpl;
import com.project.service.UserService;
import com.project.validator.UserEditValidator;
import com.project.validator.UserValidator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AndroidController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserEditValidator userEditValidator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @RequestMapping(value = "/unique_user", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Boolean username(User userForm) {
        if (userRepository.findByUsername(userForm.getUsername()) != null) {
            userDetailsServiceImpl.loadUserByUsername(userForm.getUsername());
            return false;
        } else {
            return true;
        }
    }

    @RequestMapping(value = "/logina", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Złe hasło lub login.");
            return "Wrong.";
        }
        if (logout != null) {
            model.addAttribute("message", "Wylogowano pomyślnie.");
            return "Wrong2.";
        }
        return "Zalogowanie udane.";
    }


    @RequestMapping(value = "/registrationa", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registrationa", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, String stringDate, BindingResult bindingResult, Model model) throws ParseException {
        if (stringDate != null) {
            Date dateOfBirth = new SimpleDateFormat("dd/mm/yyyy").parse(stringDate);
            userForm.setDateOfBirth(dateOfBirth);
        }

        userService.save(userForm);
        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/";
    }

    @RequestMapping(value = "/user_details", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    User userDetails(User userForm) {
        User userToGet = new User();
        User userToSet = new User();
        if (userRepository.findByUsername(userForm.getUsername()) != null) {
            userToGet = userRepository.findByUsername(userForm.getUsername());
        }
        Integer id = userToGet.getId();
        String name = userToGet.getName();
        String surname = userToGet.getSurname();
        String username = userToGet.getUsername();
        Date dateOfBrith = userToGet.getDateOfBirth();
        String gender = userToGet.getGender();
        Integer weight = null;
        Integer height = null;
        String favourite = null;

        if (userToGet.getWeight() != null) {
            weight = userToGet.getWeight();
        }
        if (userToGet.getHeight() != null) {
            height = userToGet.getHeight();
        }
        if (userToGet.getFavourite() != null) {
            favourite = userToGet.getFavourite();
        }

        userService.setDetails(userToSet, id, name, surname, username, dateOfBrith, gender, weight, height, favourite);
        return userToSet;
    }
}
