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

    public List<User> getEmployeeList() {

        Session session = null;
        List<User> empList = null;
        try {
            session = HibernateUtil.getSession();
            String queryStr = "from " + User.class.getName();
            Query query = session.createQuery(queryStr);
            empList = query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ex) {
            }
        }
        return empList;
    }

    public User getEmployeeById(Long empId) {

        Session session = null;
        User emp = null;
        try {
            session = HibernateUtil.getSession();
            String queryStr = "select emp from User emp";
            emp = session.get(User.class, empId);

        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ex) {
            }
        }
        return emp;
    }








    public User insertUser(User user) {
        //TransactionTemplate.execute(session -> session.save(emp));

//        return TransactionTemplate.execute(new TransactionCallback(){
//            public User doInTransaction(Session session){
//                session.save(user);
//                return user;
//            }
//        });

        return TransactionTemplate.execute(new TransactionCallback(){
            public User doInTransaction(EntityManager entityManager){
                entityManager.persist(user);
                return user;
            }
        });

    }


//
//    public User deleteEmployee(User user) {
//        //TransactionTemplate.execute(session -> session.save(emp));
//
//        return TransactionTemplate.execute(new TransactionCallback(){
//            public User doInTransaction(Session session){
//                session.delete(user);
//                return user;
//            }
//        });
//
//    }

//    public void deleteEmployee(User emp) {
//
//        Session session = null;
//        Transaction transaction = null;
//        try {
//            session = HibernateUtil.getSession();
//            transaction = session.beginTransaction();
//            session.delete(emp);
//            transaction.commit();
//            System.out.println("deleted employee: " + emp.getName());
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            // handle exception here
//            if (transaction != null) transaction.rollback();
//        } finally {
//            try {
//                if (session != null) session.close();
//            } catch (Exception ex) {
//            }
//        }
//    }


//entity menager zamiast session
    // hibewrnate entity menager -> persistance xml -> obiekt factory -> entity menager
    // z duzego kodu zostana dwie albo trzy linijki
    // zmiany w transaction template
    //zmieni sie entity menager

    public static void main(String[] a) {

        UsersDao empDao = new UsersDao();

        User user = new User();
        //user.setId(2);
        user.setName("Nataraja G");
        user.setSurname("Documentation");

        //User user2 = (User) empDao.insertEmployee(emp);
        System.out.println(empDao.insertUser(user));
        //System.out.println(empDao.deleteEmployee(user));
        //empDao.insertEmployee(user);


        System.out.println("---------------------------");

//        List<User> empList = empDao.getEmployeeList();
//        System.out.println("emp size: " + empList.size());
//        empList.stream().forEach(System.out::println);
//
//        System.out.println("---------------------------");
//
//        User empObj = empDao.getEmployeeById(emp.getId());
//        System.out.println(empObj);
//
//        System.out.println("---------------------------");
//        empDao.deleteEmployee(empObj);
//        //empDao.deleteEmployee(empObj);
//
//        System.out.println("---------------------------");
//
//        empList = empDao.getEmployeeList();
//        System.out.println("emp size: " + empList.size());
//        empList.stream().forEach(System.out::println);
//
//        System.out.println("---------------------------");
    }
}
