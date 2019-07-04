package pl.insert.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.insert.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TransactionTemplate {

//    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.baeldung.movie_catalog");
//
//    private static EntityManager getEntityManager() {
//        return emf.createEntityManager();
//
//    }




    public static <T> T execute(TransactionCallback transactionCallback) {


        EntityManager entityManager= getEntityManager();



//        Session session = null;
//        Transaction transaction = null;

        T t = null;

        try {
//            session = HibernateUtil.getSession();
//            transaction = session.beginTransaction();


            //t = (T) transactionCallback.doInTransaction(session);
            t = (T) transactionCallback.doInTransaction(entityManager);


            entityManager.getTransaction().commit();


//            transaction.commit();


        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
//            if (transaction != null) transaction.rollback();
        } finally {
            try {
//                if (session != null) session.close();
            } catch (Exception ex) {
            }
        }
        return t;
    }
}
