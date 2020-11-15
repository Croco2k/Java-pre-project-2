package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void update(int id, User user) {
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
        save(userToBeUpdated);
    }

    @Transactional
    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
}
