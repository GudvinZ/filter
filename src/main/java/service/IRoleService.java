package service;

import model.Role;

import java.util.List;

public interface IRoleService {

    boolean add(Role role);

    void deleteById(Long id);

    void deleteAll();

    boolean update(Role role) ;

    List<Role> getAll();

    Role getUniqueByParam(Object param, String fieldName);

    List<Role> getListByParam(Object param, String fieldName);
}
