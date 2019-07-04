package pl.insert.hibernate;

import org.hibernate.Session;

import javax.persistence.EntityManager;

public interface TransactionCallback<T> {

    T doInTransaction(EntityManager entityManager);

}
