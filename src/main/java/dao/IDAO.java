package dao;

import model.User;

import java.util.List;

public interface IDAO<T> {
     void add(T entity);

     void deleteById(Long id);

     void deleteAll();

    void update(T entity);

    T getUniqueByParam(Object param, String fieldName);

    List<T> getListByParam(Object param, String fieldName);

    List<T> getAll();
}
