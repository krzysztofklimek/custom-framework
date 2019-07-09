package pl.insert.daoProxy;

import pl.insert.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProxyUserDao implements InterfaceUserDao {


    private final  RealUserDao realUserDao;

    public ProxyUserDao(RealUserDao realUserDao) {
        this.realUserDao = realUserDao;
    }


    @Override
    public void save(User user) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAService");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        realUserDao.save(user);

        entityManager.getTransaction().commit();

    }
}