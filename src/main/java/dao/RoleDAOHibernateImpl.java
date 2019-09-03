package dao;

import model.Role;
import org.hibernate.SessionFactory;

public class RoleDAOHibernateImpl extends AbstractHibernateDao<Role> {
    private static RoleDAOHibernateImpl instance;

    public static RoleDAOHibernateImpl getInstance(Class modelClass, SessionFactory sessionFactory) {
        if (instance == null)
            instance = new RoleDAOHibernateImpl(modelClass, sessionFactory);
        return instance;
    }

    private RoleDAOHibernateImpl(Class modelClass, SessionFactory sessionFactory) {
        super.setModelClass(modelClass);
        super.setSessionFactory(sessionFactory);
    }
}
