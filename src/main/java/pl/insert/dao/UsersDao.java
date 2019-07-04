package pl.insert.dao;

import pl.insert.hibernate.HibernateUtil;
import pl.insert.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    public void insertEmployee(User emp) {

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSession();  //tutaj koniec
            transaction = session.beginTransaction();
            session.save(emp);
            System.out.println("inserted employee: " + emp.getName());
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
            if (transaction != null) transaction.rollback();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ex) {
            }
        }
    }

    public void deleteEmployee(User emp) {

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            session.delete(emp);
            transaction.commit();
            System.out.println("deleted employee: " + emp.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
            if (transaction != null) transaction.rollback();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ex) {
            }
        }
    }

    public static void main(String[] a) {

        UsersDao empDao = new UsersDao();

        User emp = new User();
        emp.setName("Babu");
        emp.setSurname("Security");
        empDao.insertEmployee(emp);

        System.out.println("---------------------------");

        List<User> empList = empDao.getEmployeeList();
        System.out.println("emp size: " + empList.size());
        empList.stream().forEach(System.out::println);

        System.out.println("---------------------------");

        User empObj = empDao.getEmployeeById(emp.getId());
        System.out.println(empObj);

        System.out.println("---------------------------");
        empDao.deleteEmployee(empObj);

        System.out.println("---------------------------");

        empList = empDao.getEmployeeList();
        System.out.println("emp size: " + empList.size());
        empList.stream().forEach(System.out::println);

        System.out.println("---------------------------");
    }
}
