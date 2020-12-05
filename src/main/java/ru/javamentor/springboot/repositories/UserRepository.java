package ru.javamentor.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.springboot.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByFirstName(String firstName);

}
