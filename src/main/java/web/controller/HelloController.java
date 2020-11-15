package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDAO;
import web.model.User;

@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping()
    public String getUsers(ModelMap model) {
        model.addAttribute("users", userDAO.getUsers());
        return "index";
    }

    @GetMapping(value = "/{id}")
    public String getUserById(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("users", userDAO.getUserById(id));
        return "user";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userDAO.save(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("user", userDAO.getUserById(id));
        return "edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userDAO.update(id, user);
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        userDAO.delete(id);
        return "redirect:/";
    }

}