package pl.insert.hibernate;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;

public interface TransactionCallback<T> {

    T doInTransaction(EntityManager entityManager) throws InvocationTargetException, IllegalAccessException;

}
