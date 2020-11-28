package web.dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Component
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<User> getUsers() {
        return entityManager.createQuery("SELECT u FROM User u").getResultList();
    }

    @Transactional
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public User getUserByName(String firstName) {
        return (User) entityManager.createQuery("select u from User u where u.firstName = :firstName")
                .setParameter("firstName", firstName).getSingleResult();
    }

    @Transactional
    public Role getRoleByName(String role) {
        return (Role) entityManager.createQuery("select r from Role r where r.role = :role")
                .setParameter("role", role).getSingleResult();
    }

    @Transactional
    public void save(User user, Set<Role> roles) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRoles(roles);
        entityManager.persist(user);
    }

    @Transactional
    public void update(int id, User user, Set<Role> roles) {
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
        userToBeUpdated.setRoles(roles);
        entityManager.persist(userToBeUpdated);
    }

    @Transactional
    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
}
