//https://www.baeldung.com/java-dynamic-proxies

package pl.insert;

import pl.insert.configuration.Configuration;
import pl.insert.context.ApplicationContext;
import pl.insert.dao.UserDao;
import pl.insert.model.User;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String [] args) throws InterruptedException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

//---------------------------------------------------------------------------------------TEST PROXY Z ENTITY MANAGEREM

        ApplicationContext applicationContext = new ApplicationContext(Configuration.class);
        UserDao realUserDaoBean = (UserDao) applicationContext.getBean("userDao");




        //--------------------dodawanie do bazy
        User user = new User();
        user.setName("dynamic123Proxy");
        user.setSurname("dynamic123Proxy");
        realUserDaoBean.persist(user);

        //--------------------metoda bez transactional
        String str = realUserDaoBean.test();
        System.out.println(str);

        //--------------------wyświetlanie całej listy
        List<User> users = new ArrayList<>();
        users = (List<User>) realUserDaoBean.getUsersList();
        System.out.println(users);

        //--------------------wyszukiwaqnie użytkownika
        User userById = realUserDaoBean.getUserById((long) 5);
        System.out.println(userById);

        //--------------------usuwanie użytkownika
        User userToDelete = new User();
        userToDelete.setId(7);
        userToDelete.setName("Babu");
        userToDelete.setSurname("Security");
        realUserDaoBean.deleteUser(userToDelete);



////---------------------------------------------------------------------------------------AUTOMATYZACJA WZORCA PROXY
//        UserDao realUserDao = new UserDaoImpl();
//        DynamicInvocationHandler dynamicInvocationHandler = new DynamicInvocationHandler(realUserDao);
//
//        UserDao dynamicProxy = (UserDao) Proxy.newProxyInstance(
//                UserDao.class.getClassLoader(),
//                new Class[]{UserDao.class},
//                dynamicInvocationHandler
//        );
//
//
//
//
//        //--------------------dodawanie do bazy
//        User user = new User();
//        user.setName("dynamic123Proxy");
//        user.setSurname("dynamic123Proxy");
//        dynamicProxy.persist(user);
//
//        //--------------------metoda bez transactional
//        String str = dynamicProxy.test();
//        System.out.println(str);
//
//        //--------------------wyświetlanie całej listy
//        List<User> users = new ArrayList<>();
//        users = (List<User>) dynamicProxy.getUsersList();
//        System.out.println(users);
//
//        //--------------------wyszukiwaqnie użytkownika
//        User userById = dynamicProxy.getUserById((long) 5);
//        System.out.println(userById);
//
//        //--------------------usuwanie użytkownika
//        User userToDelete = new User();
//        userToDelete.setId(7);
//        userToDelete.setName("Babu");
//        userToDelete.setSurname("Security");
//        dynamicProxy.deleteUser(userToDelete);



//---------------------------------------------------------------------------------------WYKORZYSTANI WZORCA PROXY
//        User user = new User();
//        user.setName("dynamicProxy");
//        user.setSurname("dynamicProxy");
//
//        UserDao realUserDao = new UserDaoImpl();
//        UserDao proxyUserDao = new ProxyUserDao((UserDaoImpl) realUserDao);
//
//        proxyUserDao.save(user);



//---------------------------------------------------------------------------------------TEST WĄTKÓW >>> THREAD LOCAL
//        ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
//
//        threadLocal.remove();
//
//        Thread thread1 = new Thread(){
//            public void run(){
//                try {
//                    threadLocal.set(1);
//
//
//                    System.out.println(threadLocal.get());
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }finally {
//                    threadLocal.remove();
//                }
//
//                System.out.println("Thread 1 umarł");
//            }
//        };
//
//        Thread thread2 = new Thread(){
//            public void run(){
//                System.out.println("Thread 2:" + threadLocal.get());
//                //threadLocal.set(2);
//                //System.out.println(threadLocal.get());
//                //threadLocal.remove();
//            }
//        };
//
//        Thread thread3 = new Thread(){
//            public void run(){
//                threadLocal.set(3);
//                System.out.println("Thread 3:" + threadLocal.get());
//                //threadLocal.remove();
//            }
//        };
//
//        thread1.start();
//        Thread.sleep(500);
//        thread2.start();
//        Thread.sleep(500);
//        thread3.start();
//
//        threadLocal.remove();




    }
}
