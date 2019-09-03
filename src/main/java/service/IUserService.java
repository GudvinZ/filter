package service;

import model.User;
import util.factory.abstractDAOFactory;

import java.util.List;

public interface IUserService {

    boolean addUser(User user);

    boolean validateUser(String login, String password);

    void deleteUserById(Long id);

    void deleteAllUsers();

    boolean updateUser(User user);

    List<User> getAllUsers();

    User getUserByParam(Object param, String fieldName);

    List<User> getUsersByParam(Object param, String fieldName);
}
