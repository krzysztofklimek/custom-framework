package pl.insert.daoProxy;

import pl.insert.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProxyUserDao implements InterfaceUserDao {


    private final  RealUserDao realUserDao;

    //to chyba nie powinnno być public
    public static ThreadLocal<EntityManager> threadLocalStorage = new ThreadLocal<EntityManager>();


    public ProxyUserDao(RealUserDao realUserDao) {
        this.realUserDao = realUserDao;
    }


    @Override
    public void save(User user) {

        try{
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAService");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            threadLocalStorage.set(entityManager);

            realUserDao.save(user);

            entityManager.getTransaction().commit();

        }catch(Exception e){}
        finally {
            threadLocalStorage.remove();
        }


    }

}
