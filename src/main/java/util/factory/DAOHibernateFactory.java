package util.factory;

import dao.AbstractHibernateDao;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import util.DBHelper;

public class DAOHibernateFactory extends abstractDAOFactory {
    private static DAOHibernateFactory instance;
    private SessionFactory sessionFactory;

    static DAOHibernateFactory getInstance() {
        if (instance == null) {
            instance = new DAOHibernateFactory();
        }
        return instance;
    }

    @Override
    public AbstractHibernateDao<?> createDAO(Class modelClass) {
        return AbstractHibernateDao.createDAO(modelClass, sessionFactory);
    }

    private DAOHibernateFactory() {
        this.sessionFactory = DBHelper.INSTANCE.getConfiguration()
                .buildSessionFactory(
                        new StandardServiceRegistryBuilder()
                                .applySettings(DBHelper.INSTANCE.getConfiguration().getProperties())
                                .build());
    }
}
