package pl.insert.daoProxy;

import pl.insert.model.User;

import javax.persistence.EntityManager;

public class RealUserDao implements InterfaceUserDao {


    private EntityManager entityManager;



    @Override
    public void save(User user) {
        entityManager.persist(user);
    }
}
