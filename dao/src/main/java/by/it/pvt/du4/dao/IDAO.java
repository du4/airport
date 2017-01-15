package by.it.pvt.du4.dao;

import by.it.pvt.du4.dao.exceptions.DaoException;

import java.util.List;

interface IDAO<TYPE> {
    TYPE read(int id) throws DaoException;
    int create(TYPE entity) throws DaoException;
    boolean update(TYPE entity) throws DaoException;
    boolean delete(TYPE entity) throws DaoException;

    List<TYPE> getAll(String WhereAndOrder) throws DaoException;

}
