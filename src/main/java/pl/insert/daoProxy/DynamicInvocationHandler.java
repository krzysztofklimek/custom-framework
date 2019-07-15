package pl.insert.daoProxy;


import pl.insert.annotation.Transactional;
import pl.insert.daoProxy.InterfaceUserDao;
import pl.insert.hibernate.TransactionCallback;
import pl.insert.hibernate.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DynamicInvocationHandler implements InvocationHandler {


    private final InterfaceUserDao realUserDao;

    //public static ThreadLocal<EntityManager> threadLocalStorage = new ThreadLocal<EntityManager>();

    public DynamicInvocationHandler (InterfaceUserDao realUserDao){
        this.realUserDao = realUserDao;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if Transactional



        TransactionCallback<Object> transactionCallback = new TransactionCallback() {
            @Override
            public Object doInTransaction(EntityManager entityManager) throws InvocationTargetException, IllegalAccessException {
                return method.invoke(realUserDao, args);
            }
        };
        return TransactionTemplate.execute(transactionCallback);
    }

    private boolean ifTransactional(){
asdfasd
    }



//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//
//
//        try{
//            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAService");
//            EntityManager entityManager = entityManagerFactory.createEntityManager();
//            entityManager.getTransaction().begin();
//
//            threadLocalStorage.set(entityManager);
//
//            method.invoke(realUserDao, args);
//
//            entityManager.getTransaction().commit();
//
//            return "Success";
//        }catch(Exception e){}
//        finally {
//            threadLocalStorage.remove();
//        }
//
//
//
//        return "Failure";
//    }
}
