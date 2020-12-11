package ru.javamentor.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springboot.model.User;
import ru.javamentor.springboot.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/user/{id}")
    public User update(@RequestBody User user, @PathVariable Long id) {
        return userService.update(id, user);
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}