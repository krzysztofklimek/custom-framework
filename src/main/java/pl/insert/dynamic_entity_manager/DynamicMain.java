package pl.insert.dynamic_entity_manager;

import pl.insert.model.User;

import javax.persistence.EntityManager;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class DynamicMain {


    public static void main(String [] args){

        DynamicProxyEntityManager handler = new DynamicProxyEntityManager();

        EntityManager obj = (EntityManager) Proxy.newProxyInstance(
                EntityManager.class.getClassLoader(),
                new Class[]{EntityManager.class},
                handler
        );

        //--------------------dodawanie do bazy
        User user = new User();
        user.setName("pppppppp");
        user.setSurname("dynamic123Proxy");
        obj.persist(user);

        //--------------------wyświetlanie całej listy
        List<User> users = new ArrayList<>();
        users = (List<User>) obj.createQuery("SELECT user FROM User user").getResultList();
        System.out.println(users);

        //--------------------wyszukiwaqnie użytkownika
        User userById = obj.find(User.class, Long.valueOf(31));
        System.out.println(userById);

        //--------------------usuwanie użytkownika
        User userToDelete = new User();
        userToDelete.setId(72);
        userToDelete.setName("Babu");
        userToDelete.setSurname("Security");
        obj.remove(obj.contains(userToDelete) ? userToDelete : obj.merge(userToDelete));
    }



}
