package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDAO;
import web.model.Role;
import web.model.User;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin/")
public class HelloController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/index")
    public String getUsers(ModelMap model) {
        model.addAttribute("users", userDAO.getUsers());
        return "index";
    }

    @GetMapping("/{id}")
    public String getUserById(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("users", userDAO.getUserById(id));
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
            roleSet.add(userDAO.getRoleByName(roles));
        }
        userDAO.save(user, roleSet);
        return "redirect:index";
    }

    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("user", userDAO.getUserById(id));
        return "edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id, @RequestParam("role") String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(userDAO.getRoleByName(roles));
        }
        userDAO.update(id, user, roleSet);
        return "redirect:index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(value = "id") Integer id) {
        userDAO.delete(id);
        return "redirect:index";
    }

}