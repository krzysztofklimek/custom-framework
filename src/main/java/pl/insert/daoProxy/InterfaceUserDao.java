package pl.insert.daoProxy;

import pl.insert.model.User;

import java.util.List;

public interface InterfaceUserDao {


    void persist(User user);
    List<?> getUsersList();
    User getUserById(Long userId);
    void deleteUser(User user);
    String test();

}
