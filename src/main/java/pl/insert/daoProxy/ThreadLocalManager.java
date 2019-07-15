package pl.insert.daoProxy;

import javax.persistence.EntityManager;

public class ThreadLocalManager {

    private static final ThreadLocal<EntityManager> THREAD_LOCAL = new ThreadLocal<>();


    public static EntityManager getThreadLocal(){
        return THREAD_LOCAL.get();
    }

    public static void setThreadLocal(EntityManager entityManager){
        THREAD_LOCAL.set(entityManager);
    }

    public static void removeThreadLocal(){
        THREAD_LOCAL.remove();
    }

}
