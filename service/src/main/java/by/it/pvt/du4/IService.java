package by.it.pvt.du4;

import by.it.pvt.du4.exceptions.ServiceException;

import java.io.Serializable;
import java.util.List;

public interface IService<T>{

    void saveOrUpdate(T t) throws ServiceException;

    T getById(Serializable id) throws ServiceException;

//    List<T> getAllByMapRestriction(Map<String, String> restriction);

    T loadById(Serializable id) throws ServiceException;

    void delete(T t) throws ServiceException;

//    List<T> getAll() throws ServiceException;

}
