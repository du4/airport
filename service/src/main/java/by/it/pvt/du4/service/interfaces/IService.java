package by.it.pvt.du4.service.interfaces;

import by.it.pvt.du4.service.exceptions.ServiceException;

import java.io.Serializable;
import java.util.List;

public interface IService<T>{
    /**
     * Method save persistent entity or update if <T> class with the same ID exist in the 1st level cache
     * @param t - Object to update
     * @throws ServiceException
     */
    void create(T t) throws ServiceException;

    /**
     * Method to getting <T> t witch have ID=id
     * @param id
     * @return Instance of <T> Class
     * @throws ServiceException
     */
    T get(Class clazz, Serializable id) throws ServiceException;

    void update(T t) throws ServiceException;

    void delete(T t) throws ServiceException;

    List<T> getAll(Class clazz) throws ServiceException;



}
