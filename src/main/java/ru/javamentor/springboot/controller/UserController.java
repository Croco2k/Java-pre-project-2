package ru.javamentor.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javamentor.springboot.model.User;
import ru.javamentor.springboot.service.UserServiceImpl;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("")
    public String printUserInfo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", userService.getUserByName(auth.getName()));
        return "userPage";
    }

}
