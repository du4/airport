package by.it.pvt.du4.service;

import by.it.pvt.du4.dao.IDao;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.service.exceptions.ServiceException;
import by.it.pvt.du4.service.interfaces.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BaseService<T> implements IService<T> {
    private static final Logger LOG = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    private IDao<T> baseDao;

    public BaseService() {
    }

    @Override
    public void update(T entity) throws ServiceException {
        try {
            baseDao.update(entity);
        }catch (Exception e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public T get(Class clazz, Serializable id) throws ServiceException {
        LOG.info("Get class by id:" + id);
        T entity = null;
        try {
            entity = (T)baseDao.get(clazz, id);
        }catch (Exception e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
        return  entity;
    }

    @Override
    public void create(T t) throws ServiceException {
        try {
            baseDao.create(t);
        }catch (Exception e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(T entity) throws ServiceException {
        try {
            baseDao.delete(entity);
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<T> getAll(Class clazz) throws ServiceException {
        try {
            return  baseDao.getAll(clazz);
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

}
