package com.kama.springboot.controller;

import com.kama.springboot.model.User;
import com.kama.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String pageForUser (Model model, Principal principal) {
        model.addAttribute("user",userService.getUserByUsername(principal.getName()));
        return "pages/user";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "pages/login";
    }
}
