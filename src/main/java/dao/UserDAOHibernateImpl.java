package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserDAOHibernateImpl extends AbstractHibernateDao<User> {
    private static UserDAOHibernateImpl instance;

    public static UserDAOHibernateImpl getInstance(Class modelClass, SessionFactory sessionFactory) {
        if (instance == null)
            instance = new UserDAOHibernateImpl(modelClass, sessionFactory);
        return instance;
    }


    private UserDAOHibernateImpl(Class modelClass, SessionFactory sessionFactory) {
        super.setModelClass(modelClass);
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public void add(User user) {
        try (Session session = super.getSessionFactory().openSession()) {
            Transaction trx = session.beginTransaction();
            session.merge(user);
            trx.commit();
        }
    }
}
