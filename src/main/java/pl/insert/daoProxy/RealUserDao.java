package pl.insert.daoProxy;

import pl.insert.annotation.Transactional;
import pl.insert.model.User;

import javax.persistence.EntityManager;

public class RealUserDao implements InterfaceUserDao {


    private EntityManager entityManager;



    @Override
    @Transactional
    public void save(User user) {
        //entityManager = ProxyUserDao.threadLocalStorage.get();
        entityManager = ThreadLocalManager.getThreadLocal();
        entityManager.persist(user);
    }


    @Override
    public String test(){
        return "testowa metoda bez interakcji z bazą danych";
    }








}
