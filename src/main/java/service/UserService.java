package service;

import dao.IDAO;
import model.User;
import util.factory.abstractDAOFactory;

import java.util.List;

public class UserService implements Service<User> {
    private static UserService instance;
    private IDAO<User> dao;

    public static UserService getInstance() {
        if (instance == null)
            instance = new UserService();
        return instance;
    }

    private UserService() {
        this.dao = abstractDAOFactory.createFactoryByProperties().createDAO(User.class);
    }

    @Override
    public boolean add(User user) {
        if (validate(user.getLogin(), user.getPassword()))
            return false;
        dao.add(user);
        return true;
    }

    public boolean validate(String login, String password) {
        User user = dao.getUniqueByParam(login, "login");
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }

    @Override
    public boolean update(User user) {
        if (validate(user.getLogin(), user.getPassword()))
            return false;
        dao.update(user);
        return true;
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public User getUniqueByParam(Object param, String fieldName) {
        return dao.getUniqueByParam(param, fieldName);
    }

    @Override
    public List<User> getListByParam(Object param, String fieldName) {
        return dao.getListByParam(param, fieldName);
    }
}
