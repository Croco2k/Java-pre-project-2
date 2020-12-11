package ru.javamentor.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javamentor.springboot.service.UserServiceImpl;

@Controller
@RequestMapping("/admin/")
public class SimpleController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("")
    public String getAllUsers(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("springUser", userService.getUserByName(auth.getName()));
        return "adminPage";
    }


}
