package service;

import dao.DAO;
import model.User;
import util.factory.UserDAOFactory;

import java.util.List;

public class UserService {
    private static UserService instance;
    private DAO dao;

    public static UserService getInstance() {
        if (instance == null)
            instance = new UserService();
        return instance;
    }

    private UserService() {
        this.dao = UserDAOFactory.createFactoryByProperties().createDAO();
    }

    public boolean addUser(User user) {
        if (dao.validateUser(user.getLogin(), user.getPassword()))
            return false;
        dao.addUser(user);
        return true;
    }

    public void deleteUserById(Long id) {
        dao.deleteUserById(id);
    }

    public void deleteAllUsers() {
        dao.deleteAllUsers();
    }

    public void updateUser(User user) {
        dao.updateUser(user);
    }

    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    public boolean validateUser(String login, String password) {
        return dao.validateUser(login, password);
    }

    public User getUserByLoginAndPassword(String login, String password) {
        return dao.getUserByLoginAndPassword(login, password);
    }
}
