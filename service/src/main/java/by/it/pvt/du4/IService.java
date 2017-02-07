package by.it.pvt.du4;

import by.it.pvt.du4.exceptions.ServiceException;

import java.io.Serializable;
import java.util.List;

public interface IService<T>{
    /**
     * Method save persistent entity or update if <T> class with the same ID exist in the 1st level cache
     * @param t - Object to saveOrUpdate
     * @throws ServiceException
     */
    void saveOrUpdate(T t) throws ServiceException;

    /**
     * Method to getting <T> t witch have ID=id
     * @param id
     * @return Instance of <T> Class
     * @throws ServiceException
     */
    T get(Serializable id) throws ServiceException;

//    List<T> getAllByMapRestriction(Map<String, String> restriction);

    T load(Serializable id) throws ServiceException;

    void delete(T t) throws ServiceException;

//    List<T> getAll() throws ServiceException;

}
