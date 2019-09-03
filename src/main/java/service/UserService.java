package service;

import dao.IDAO;
import model.User;
import util.factory.abstractDAOFactory;

import java.util.List;

public class UserService implements IUserService {
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
    public boolean addUser(User user) {
        if (validateUser(user.getLogin(), user.getPassword()))
            return false;
        dao.add(user);
        return true;
    }

    public boolean validateUser(String login, String password) {
        User user = dao.getUniqueByParam(login, "login");
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public void deleteUserById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public void deleteAllUsers() {
        dao.deleteAll();
    }

    @Override
    public boolean updateUser(User user) {
        if (validateUser(user.getLogin(), user.getPassword()))
            return false;
        dao.update(user);
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAll();
    }

    @Override
    public User getUserByParam(Object param, String fieldName) {
        return dao.getUniqueByParam(param, fieldName);
    }

    @Override
    public List<User> getUsersByParam(Object param, String fieldName) {
        return dao.getListByParam(param, fieldName);
    }
}
