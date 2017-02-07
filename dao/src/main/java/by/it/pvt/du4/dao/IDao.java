package by.it.pvt.du4.dao;

import by.it.pvt.du4.dao.exceptions.DaoException;

import java.io.Serializable;

interface IDao<T> {

    void saveOrUpdate(T t) throws DaoException;

    T get(Serializable id) throws DaoException;

    T load(Serializable id) throws DaoException;

    void delete(T t) throws DaoException;
}

