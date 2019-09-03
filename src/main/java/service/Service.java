package service;

import model.User;

import java.util.List;

public interface Service<T> {
    boolean add(T entity);

    void deleteById(Long id);

    void deleteAll();

    boolean update(T entity);

    List<T> getAll();

    T getUniqueByParam(Object param, String fieldName);

    List<T> getListByParam(Object param, String fieldName);
}
