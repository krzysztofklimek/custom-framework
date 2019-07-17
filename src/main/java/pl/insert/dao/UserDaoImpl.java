package pl.insert.dao;

import pl.insert.annotation.Autowired;
import pl.insert.annotation.Qualifier;
import pl.insert.annotation.Transactional;
import pl.insert.model.User;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDaoImpl implements UserDao {


    @Autowired
    @Qualifier(name = "entityManager")
    private EntityManager entityManager;


    @Override
    @Transactional
    public void persist(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public List<?> getUsersList() {
        List<?> users = entityManager.createQuery("SELECT user FROM User user").getResultList();
        return users;
    }

    @Override
    @Transactional
    public User getUserById(Long userId) {
        User user = entityManager.find(User.class, Long.valueOf(userId));
        return user;
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }


    @Override
    public String test() {
        return "testowa metoda bez interakcji z bazą danych";
    }

}
