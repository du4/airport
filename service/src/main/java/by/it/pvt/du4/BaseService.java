package by.it.pvt.du4;

import by.it.pvt.du4.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class BaseService <T> implements IService<T> {
    private static final Logger LOG = LoggerFactory.getLogger(BaseService.class);

    @Override
    public void saveOrUpdate(T o) throws ServiceException {

    }

    @Override
    public T getById(Serializable id) throws ServiceException {
        return null;
    }

    @Override
    public T loadById(Serializable id) throws ServiceException {
        return null;
    }

    @Override
    public void delete(T o) throws ServiceException {

    }



}
