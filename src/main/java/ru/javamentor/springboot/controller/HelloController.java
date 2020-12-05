package ru.javamentor.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springboot.model.Role;
import ru.javamentor.springboot.model.User;
import ru.javamentor.springboot.service.UserServiceImpl;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin/")
public class HelloController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/index")
    public String getUsers(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("springUser", userService.getUserByName(auth.getName()));
        return "index";
    }

    @PostMapping("")
    public String createUser(@ModelAttribute("user") User user, @RequestParam("role") String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(userService.getRoleByName(roles));
        }
        userService.save(user, roleSet);
        return "redirect:index";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("user") User user, @RequestParam("id") Long id,
                         @RequestParam("role") String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(userService.getRoleByName(roles));
        }
        userService.update(id, user, roleSet);
        return "redirect:index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(value = "id") Long id) {
        userService.delete(id);
        return "redirect:index";
    }

}