package ru.javamentor.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
        model.addAttribute("users", userService.getUsers());
        return "index";
    }

    @GetMapping("/{id}")
    public String getUserById(ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("users", userService.getUserById(id));
        return "user";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
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

    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id, @RequestParam("role") String[] role) {
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