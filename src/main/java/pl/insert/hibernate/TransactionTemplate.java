package pl.insert.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.insert.daoProxy.ThreadLocalManager;
import pl.insert.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.InvocationTargetException;

public class TransactionTemplate {


    public static <T> T execute(TransactionCallback transactionCallback) throws InvocationTargetException, IllegalAccessException {

        T t = null;

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAService");
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try{
            ThreadLocalManager.setThreadLocal(entityManager);
            entityManager.getTransaction().begin();

            t = (T) transactionCallback.doInTransaction(entityManager);

            entityManager.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            entityManager.close();
            ThreadLocalManager.removeThreadLocal();
        }


        return t;
    }




//    public static <T> T execute(TransactionCallback transactionCallback) {
//
//        T t = null;
//
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAService");
//        EntityManager entityManager= entityManagerFactory.createEntityManager();
//
//        entityManager.getTransaction().begin();
//        t = (T) transactionCallback.doInTransaction(entityManager);
//        entityManager.getTransaction().commit();
//
//        return t;
//    }





//    public static <T> T execute(TransactionCallback transactionCallback) {
//
//
//        EntityManager entityManager= getEntityManager();
//
//
//
////        Session session = null;
////        Transaction transaction = null;
//
//        T t = null;
//
//        try {
////            session = HibernateUtil.getSession();
////            transaction = session.beginTransaction();
//
//
//            //t = (T) transactionCallback.doInTransaction(session);
//            t = (T) transactionCallback.doInTransaction(entityManager);
//
//
//            entityManager.getTransaction().commit();
//
//
////            transaction.commit();
//
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            // handle exception here
////            if (transaction != null) transaction.rollback();
//        } finally {
//            try {
////                if (session != null) session.close();
//            } catch (Exception ex) {
//            }
//        }
//        return t;
//    }
}
