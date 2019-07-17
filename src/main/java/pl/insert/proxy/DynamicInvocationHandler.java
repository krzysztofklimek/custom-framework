package pl.insert.proxy;


import pl.insert.annotation.Transactional;
import pl.insert.hibernate.TransactionCallback;
import pl.insert.hibernate.TransactionTemplate;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DynamicInvocationHandler implements InvocationHandler {


    private final Object realUserDao;

    public DynamicInvocationHandler(Object realUserDao) {
        this.realUserDao = realUserDao;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        Method[] methods = this.realUserDao.getClass().getDeclaredMethods();

        for (Method m : methods) {


            //SPRAWDZA CZY W OGOLE ZNALAZŁO METODĘ Z TAKĄ NAZWĄ
            if (m.getName().equals(method.getName())) {

                //SPRAWDZA CZY JEST ADNOTACJA TRANSACTIONAL
                if (!m.isAnnotationPresent(Transactional.class)) {
                    return method.invoke(this.realUserDao, args);
                } else {
                    return TransactionTemplate.execute(new TransactionCallback() {
                        @Override
                        public Object doInTransaction(EntityManager entityManager) throws InvocationTargetException, IllegalAccessException {
                            return method.invoke(realUserDao, args);
                        }
                    });
                }
            }
        }
        return null;
    }


}
