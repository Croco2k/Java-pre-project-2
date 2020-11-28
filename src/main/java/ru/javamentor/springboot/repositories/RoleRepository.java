package ru.javamentor.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.javamentor.springboot.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByRole(String role);

}
