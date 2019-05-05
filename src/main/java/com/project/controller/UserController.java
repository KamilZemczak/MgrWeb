package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;

import com.project.model.User;
import com.project.service.SecurityService;
import com.project.service.UserService;
import com.project.validator.UserEditValidator;
import com.project.validator.UserValidator;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserEditValidator userEditValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userForm);
        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Złe hasło lub login.");
        }
        if (logout != null) {
            model.addAttribute("message", "Wylogowano pomyślnie.");
        }
        return "login";
    }

    @RequestMapping(value = "/user-account", method = RequestMethod.GET)
    public String updateProfile(Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("userEdit", new User());
        User dupa = userService.getCurrentUser();
        Date date = dupa.getDateOfBirth();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(date);
        dupa.setEditDate(s);
        request.setAttribute("user", dupa);

        return "editprofile";
    }

    @RequestMapping(value = "/user-account", method = RequestMethod.POST)
    public String updateProfile(@ModelAttribute("userEdit") User userForm, BindingResult bindingResult, HttpServletRequest request) {
        userEditValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "editprofile";
        }
        userService.updateProfile(userService.getCurrentUser(), userForm);
        return "redirect:/";
    }
}
