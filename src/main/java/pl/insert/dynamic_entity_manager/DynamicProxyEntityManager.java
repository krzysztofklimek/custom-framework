package pl.insert.dynamic_entity_manager;

import pl.insert.daoProxy.ThreadLocalManager;
import pl.insert.hibernate.TransactionCallback;
import pl.insert.hibernate.TransactionTemplate;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DynamicProxyEntityManager implements InvocationHandler {


//    private final Object entity;
//
//
//    public DynamicProxyEntityManager(Object object){
//        this.entity = object;
//    }


    @Override
    public Object invoke(Object realSubject, Method method, Object[] args) throws Throwable {


        return TransactionTemplate.execute(new TransactionCallback(){
            @Override
            public Object doInTransaction(EntityManager entityManager) throws InvocationTargetException, IllegalAccessException {



                return method.invoke(ThreadLocalManager.getThreadLocal(), args);
            }
        });

    }
}
