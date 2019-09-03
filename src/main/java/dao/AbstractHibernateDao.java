package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.security.InvalidParameterException;
import java.util.List;

public abstract class AbstractHibernateDao<T> implements IDAO<T> {

    private Class modelClass;
    private SessionFactory sessionFactory;

    public static AbstractHibernateDao<?> createDAO(Class modelClass, SessionFactory sessionFactory) {
        switch (modelClass.getSimpleName()) {
            case "User":
                return UserDAOHibernateImpl.getInstance(modelClass, sessionFactory);
            case "Role":
                return RoleDAOHibernateImpl.getInstance(modelClass, sessionFactory);
            default:
                throw new InvalidParameterException();
        }
    }

    Class getModelClass() {
        return modelClass;
    }

    SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    void setModelClass(Class modelClass) {
        this.modelClass = modelClass;
    }

    void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(T entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction trx = session.beginTransaction();
            session.persist(entity);
            trx.commit();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction trx = session.beginTransaction();
            session.createQuery("DELETE FROM " + modelClass.getSimpleName() + " WHERE id=?1")
                    .setParameter(1, id)
                    .executeUpdate();
            trx.commit();
        }
    }

    @Override
    public void deleteAll() {
        try (Session session = sessionFactory.openSession()) {
            Transaction trx = session.beginTransaction();
            session.createQuery("DELETE FROM " + modelClass.getSimpleName()).executeUpdate();
            trx.commit();
        }
    }

    @Override
    public void update(T entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction trx = session.beginTransaction();
            session.update(entity);
            trx.commit();
        }
    }

    @Override
    public T getUniqueByParam(Object param, String fieldName) {
        try (Session session = sessionFactory.openSession()) {
            return (T) session.createQuery("FROM " + modelClass.getSimpleName() + " where " + fieldName + " = ?1")
                    .setParameter(1, param)
                    .uniqueResult();
        }
    }

    @Override
    public List<T> getListByParam(Object param, String fieldName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM " + modelClass.getSimpleName() + " where " + fieldName + " = ?1")
                    .setParameter(1, param)
                    .list();
        }
    }

    @Override
    public List<T> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM " + modelClass.getSimpleName()).list();
        }
    }
}
