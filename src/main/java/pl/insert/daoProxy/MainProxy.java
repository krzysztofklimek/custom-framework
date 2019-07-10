//https://www.baeldung.com/java-dynamic-proxies

package pl.insert.daoProxy;

import pl.insert.model.User;

public class MainProxy {


    public static void main(String [] args) throws InterruptedException {


        User user = new User();
        user.setName("proxy");
        user.setSurname("proxy");

        InterfaceUserDao realUserDao = new RealUserDao();
        InterfaceUserDao proxyUserDao = new ProxyUserDao((RealUserDao) realUserDao);

        proxyUserDao.save(user);



//---------------------------------------------------------------------------------------TEST WĄTKÓW >>> THREAD LOCAL
        ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

        threadLocal.remove();

        Thread thread1 = new Thread(){
            public void run(){
                try {
                    threadLocal.set(1);


                    System.out.println(threadLocal.get());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }finally {
                    threadLocal.remove();
                }

                System.out.println("Thread 1 umarł");
            }
        };

        Thread thread2 = new Thread(){
            public void run(){
                System.out.println("Thread 2:" + threadLocal.get());
                //threadLocal.set(2);
                //System.out.println(threadLocal.get());
                //threadLocal.remove();
            }
        };

        Thread thread3 = new Thread(){
            public void run(){
                threadLocal.set(3);
                System.out.println("Thread 3:" + threadLocal.get());
                //threadLocal.remove();
            }
        };

        thread1.start();
        Thread.sleep(500);
        thread2.start();
        Thread.sleep(500);
        thread3.start();

        threadLocal.remove();
    }

}
