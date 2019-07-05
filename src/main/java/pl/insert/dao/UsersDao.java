//https://www.baeldung.com/hibernate-entitymanager
//https://www.youtube.com/watch?v=Yv2xctJxE-w&list=PL4AFF701184976B25&index=1

package pl.insert.dao;

import pl.insert.hibernate.HibernateUtil;
import pl.insert.hibernate.TransactionCallback;
import pl.insert.hibernate.TransactionTemplate;
import pl.insert.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import java.util.List;


public class UsersDao {

//    public List<User> getEmployeeList() {
//
//        Session session = null;
//        List<User> empList = null;
//        try {
//            session = HibernateUtil.getSession();
//            String queryStr = "from " + User.class.getName();
//            Query query = session.createQuery(queryStr);
//            empList = query.list();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            // handle exception here
//        } finally {
//            try {
//                if (session != null) session.close();
//            } catch (Exception ex) {
//            }
//        }
//        return empList;
//    }

    public List<User> getEmployeeList() {

        return TransactionTemplate.execute(new TransactionCallback(){
           public Object doInTransaction(EntityManager entityManager){
               List<User> users = entityManager.createQuery("SELECT user FROM User user").getResultList();
               return users;
           }
        });

    }


    public User getUserById(Long userId){

        return TransactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(EntityManager entityManager) {
                User user =entityManager.find(User.class, new Long(userId));
                return user;
            }
        });

    }



    public User insertUser(User user) {
        //TransactionTemplate.execute(session -> session.save(emp));

        return TransactionTemplate.execute(new TransactionCallback(){
            public User doInTransaction(EntityManager entityManager){
                entityManager.persist(user);
                return user;
            }
        });

    }



    public User deleteUser(User user) {
        //TransactionTemplate.execute(session -> session.save(emp));



        return TransactionTemplate.execute(new TransactionCallback(){
            public User doInTransaction(EntityManager entityManager){
                entityManager.remove(
                        entityManager.contains(user) ? user : entityManager.merge(user)
                );
                return user;
            }
        });

    }




    public static void main(String[] a) {

        UsersDao userDao = new UsersDao();

        User user = new User();
        user.setId(4);
        user.setName("Babu");
        user.setSurname("Security");



        //System.out.println(empDao.insertUser(user));
        //System.out.println(userDao.deleteUser(user));
        //System.out.println(userDao.getUserById((long) 12));
        System.out.println(userDao.getEmployeeList());
        
    }
}
