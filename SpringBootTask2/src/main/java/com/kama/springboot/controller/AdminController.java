package com.kama.springboot.controller;

import com.kama.springboot.model.User;
import com.kama.springboot.service.RoleService;
import com.kama.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String pageForAdmin(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "pages/users";
    }

    @GetMapping("/admin/new")
    public String pageCreateUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("listRoles",roleService.getAllRoles());
        return "pages/create";
    }

    @PostMapping("/admin/new")
    public String pageCreate(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete/{id}")
    public String pageDelete(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String pageEditUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user",userService.getUserById(id));
        model.addAttribute("listRoles", roleService.getAllRoles());
        return "pages/edit";
    }

    @PostMapping("/admin/edit")
    public String pageEdit(User user, @RequestParam("listRoles") ArrayList<Long>roles) {
        userService.updateUser(user);
        return "redirect:/admin";
    }


}
