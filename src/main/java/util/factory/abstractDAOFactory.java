package util.factory;

import dao.IDAO;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Properties;

public abstract class abstractDAOFactory {

    public abstract <T> IDAO<T> createDAO(Class clazz);

    public static abstractDAOFactory createFactoryByProperties() {
        Properties config = new Properties();
        try {
            config.load(abstractDAOFactory.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (config.getProperty("daoType")) {
//            case "jdbc":
//                return UserDAOJDBCFactory.getInstance();
            case "hibernate":
                return DAOHibernateFactory.getInstance();
            default:
                throw new InvalidParameterException("invalid config.properties parameter");
        }
    }
}
