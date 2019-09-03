package service;

import dao.IDAO;
import model.Role;
import util.factory.abstractDAOFactory;

import java.util.List;

public class RoleService implements Service<Role> {
    private static RoleService instance;
    private IDAO<Role> dao;

    public static RoleService getInstance() {
        if (instance == null)
            instance = new RoleService();
        return instance;
    }

    private RoleService() {
        this.dao = abstractDAOFactory.createFactoryByProperties().createDAO(Role.class);
    }


    @Override
    public boolean add(Role role) {
        if (getUniqueByParam(role.getRole(), "role") == null) {
            dao.add(role);
            return true;
        } else
            return false;
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
    public boolean update(Role role) {
        if (getUniqueByParam(role.getRole(), "role") == null) {
            dao.update(role);
            return true;
        } else
            return false;
    }

    @Override
    public List<Role> getAll() {
        return dao.getAll();
    }

    @Override
    public Role getUniqueByParam(Object param, String fieldName) {
        return dao.getUniqueByParam(param, fieldName);
    }

    @Override
    public List<Role> getListByParam(Object param, String fieldName) {
        return dao.getListByParam(param, fieldName);
    }
}
