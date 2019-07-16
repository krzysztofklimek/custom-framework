package pl.insert.daoProxy;

import pl.insert.model.User;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.util.List;
import java.util.Map;

public class EntityManagerProxy implements EntityManager {


    @Override
    public void persist(Object object){
        ThreadLocalManager.getThreadLocal().persist(object);
    }

    @Override
    public <T> T merge(T entity) {
        return ThreadLocalManager.getThreadLocal().merge(entity);
    }

    @Override
    public void remove(Object entity) {
        ThreadLocalManager.getThreadLocal().remove(entity);
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey) {
        return ThreadLocalManager.getThreadLocal().find(entityClass,primaryKey);
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey, Map<String, Object> properties) {
        return ThreadLocalManager.getThreadLocal().find(entityClass, primaryKey, properties);
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode) {
        return ThreadLocalManager.getThreadLocal().find(entityClass, primaryKey, lockMode);
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode, Map<String, Object> properties) {
        return ThreadLocalManager.getThreadLocal().find(entityClass,primaryKey,lockMode, properties);
    }

    @Override
    public <T> T getReference(Class<T> entityClass, Object primaryKey) {
        return ThreadLocalManager.getThreadLocal().getReference(entityClass, primaryKey);
    }

    @Override
    public void flush() {
        ThreadLocalManager.getThreadLocal().flush();
    }

    @Override
    public void setFlushMode(FlushModeType flushMode) {
        ThreadLocalManager.getThreadLocal().setFlushMode(flushMode);
    }

    @Override
    public FlushModeType getFlushMode() {
        return ThreadLocalManager.getThreadLocal().getFlushMode();
    }

    @Override
    public void lock(Object entity, LockModeType lockMode) {
        ThreadLocalManager.getThreadLocal().lock(entity, lockMode);
    }


    @Override
    public void lock(Object entity, LockModeType lockMode, Map<String, Object> properties) {
        ThreadLocalManager.getThreadLocal().lock(entity, lockMode, properties);
    }

    @Override
    public void refresh(Object entity) {
        ThreadLocalManager.getThreadLocal().refresh(entity);
    }

    @Override
    public void refresh(Object entity, Map<String, Object> properties) {
        ThreadLocalManager.getThreadLocal().refresh(entity, properties);
    }

    @Override
    public void refresh(Object entity, LockModeType lockMode) {
        ThreadLocalManager.getThreadLocal().refresh(entity, lockMode);
    }

    @Override
    public void refresh(Object entity, LockModeType lockMode, Map<String, Object> properties) {
        ThreadLocalManager.getThreadLocal().refresh( entity,lockMode, properties);
    }

    @Override
    public void clear() {
        ThreadLocalManager.getThreadLocal().clear();
    }

    @Override
    public void detach(Object entity) {
        ThreadLocalManager.getThreadLocal().detach(entity);
    }

    @Override
    public boolean contains(Object entity) {
        return ThreadLocalManager.getThreadLocal().contains(entity);
    }

    @Override
    public LockModeType getLockMode(Object entity) {
        return ThreadLocalManager.getThreadLocal().getLockMode(entity);
    }

    @Override
    public void setProperty(String propertyName, Object value) {
        ThreadLocalManager.getThreadLocal().setProperty(propertyName, value);
    }

    @Override
    public Map<String, Object> getProperties() {
        return ThreadLocalManager.getThreadLocal().getProperties();
    }

    @Override
    public Query createQuery(String qlString) {
        return ThreadLocalManager.getThreadLocal().createQuery(qlString);
    }

    @Override
    public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
        return ThreadLocalManager.getThreadLocal().createQuery(criteriaQuery);
    }

    @Override
    public Query createQuery(CriteriaUpdate updateQuery) {
        return ThreadLocalManager.getThreadLocal().createQuery(updateQuery);
    }

    @Override
    public Query createQuery(CriteriaDelete deleteQuery) {
        return ThreadLocalManager.getThreadLocal().createQuery(deleteQuery);
    }

    @Override
    public <T> TypedQuery<T> createQuery(String qlString, Class<T> resultClass) {
        return ThreadLocalManager.getThreadLocal().createQuery(qlString, resultClass);
    }

    @Override
    public Query createNamedQuery(String name) {
        return ThreadLocalManager.getThreadLocal().createNamedQuery(name);
    }

    @Override
    public <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass) {
        return ThreadLocalManager.getThreadLocal().createNamedQuery( name, resultClass);
    }

    @Override
    public Query createNativeQuery(String sqlString) {
        return ThreadLocalManager.getThreadLocal().createNativeQuery(sqlString);
    }

    @Override
    public Query createNativeQuery(String sqlString, Class resultClass) {
        return ThreadLocalManager.getThreadLocal().createNativeQuery( sqlString, resultClass);
    }

    @Override
    public Query createNativeQuery(String sqlString, String resultSetMapping) {
        return ThreadLocalManager.getThreadLocal().createNativeQuery(sqlString, resultSetMapping);
    }

    @Override
    public StoredProcedureQuery createNamedStoredProcedureQuery(String name) {
        return ThreadLocalManager.getThreadLocal().createNamedStoredProcedureQuery(name);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName) {
        return ThreadLocalManager.getThreadLocal().createStoredProcedureQuery(procedureName);
}

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName, Class... resultClasses) {
        return ThreadLocalManager.getThreadLocal().createStoredProcedureQuery(procedureName, resultClasses);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName, String... resultSetMappings) {
        return ThreadLocalManager.getThreadLocal().createStoredProcedureQuery(procedureName, resultSetMappings);
    }

    @Override
    public void joinTransaction() {
        ThreadLocalManager.getThreadLocal().joinTransaction();
    }

    @Override
    public boolean isJoinedToTransaction() {
        return ThreadLocalManager.getThreadLocal().isJoinedToTransaction();
    }

    @Override
    public <T> T unwrap(Class<T> cls) {
        return ThreadLocalManager.getThreadLocal().unwrap(cls);
    }

    @Override
    public Object getDelegate() {
        return ThreadLocalManager.getThreadLocal().getDelegate();
    }

    @Override
    public void close() {
        ThreadLocalManager.getThreadLocal().close();
    }

    @Override
    public boolean isOpen() {
        return ThreadLocalManager.getThreadLocal().isOpen();
    }

    @Override
    public EntityTransaction getTransaction() {
        return ThreadLocalManager.getThreadLocal().getTransaction();
    }

    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return ThreadLocalManager.getThreadLocal().getEntityManagerFactory();
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return ThreadLocalManager.getThreadLocal().getCriteriaBuilder();
    }

    @Override
    public Metamodel getMetamodel() {
        return ThreadLocalManager.getThreadLocal().getMetamodel();
    }

    @Override
    public <T> EntityGraph<T> createEntityGraph(Class<T> rootType) {
        return ThreadLocalManager.getThreadLocal().createEntityGraph(rootType);
    }

    @Override
    public EntityGraph<?> createEntityGraph(String graphName) {
        return ThreadLocalManager.getThreadLocal().createEntityGraph(graphName);
    }

    @Override
    public EntityGraph<?> getEntityGraph(String graphName) {
        return ThreadLocalManager.getThreadLocal().getEntityGraph(graphName);
    }

    @Override
    public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> entityClass) {
        return ThreadLocalManager.getThreadLocal().getEntityGraphs(entityClass);
    }


}
