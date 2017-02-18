package by.it.pvt.du4.dao;

import by.it.pvt.du4.dao.exceptions.DaoException;

import java.io.Serializable;
import java.util.List;

public interface IDao<T> {

    void create(T t) throws DaoException;

    void update(T t) throws DaoException;

    T get(Class<T>clazz,Serializable id) throws DaoException;

    void delete(T t) throws DaoException;

    List<T> getAll(Class<T>clazz) throws DaoException;

    Long getCount(Class<T>clazz) throws Exception;
}

