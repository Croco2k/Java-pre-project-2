package ru.javamentor.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.javamentor.springboot.model.Role;
import ru.javamentor.springboot.model.User;
import ru.javamentor.springboot.repositories.RoleRepository;
import ru.javamentor.springboot.repositories.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
//        return userRepository.getOne(id);
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    public Role getRoleByName(String role) {
        return roleRepository.findRoleByRole(role);
    }

    public User save(User user) {
        Set<Role> roles = new HashSet<>();
        for (Role role: user.getRoles()){
            roles.add(roleRepository.findRoleByRole(role.getRole()));
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        User userToBeUpdated = getUserById(id);
        if (!user.getFirstName().isEmpty()) {
            userToBeUpdated.setFirstName(user.getFirstName());
        }
        if (!user.getLastName().isEmpty()) {
            userToBeUpdated.setLastName(user.getLastName());
        }
        if (!user.getEmail().isEmpty()) {
            userToBeUpdated.setEmail(user.getEmail());
        }
        if (!user.getPassword().isEmpty()) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        Set<Role> roles = new HashSet<>();
        for (Role role: user.getRoles()){
            roles.add(roleRepository.findRoleByRole(role.getRole()));
        }
        userToBeUpdated.setRoles(roles);
        return userRepository.save(userToBeUpdated);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
