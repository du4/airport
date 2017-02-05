package by.it.pvt.du4.dao;


import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


public class BaseDao<T> implements IDao<T> {
    private static final Logger LOG = LoggerFactory.getLogger(BaseDao.class);


    public BaseDao() {

    }

    public void saveOrUpdate(T t) throws DaoException{
        try {
            Session session = HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
            session.saveOrUpdate(t);
            LOG.info("saveOrUpdate(t):" + t);
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("Error save or update in IDao" + e);
            throw new DaoException(e);
        }

    }

    public T get(Serializable id) throws DaoException {
        LOG.info("Get class by id:" + id);
        T t = null;
        try {
            Session session = HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
            t = (T) session.get(getPersistentClass(), id);
            LOG.info("get clazz:" + t);
        } catch (HibernateException e) {
            LOG.error("Error get " + getPersistentClass() + " in IDao" + e);
            throw new DaoException(e);
        }
        return t;
    }

    public T load(Serializable id) throws DaoException {
        LOG.info("Load class by id:" + id);
        T t = null;
        try {
            Session session = HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
            t = (T) session.load(getPersistentClass(), id);
            LOG.info("load() clazz:" + t);
            session.isDirty();
        } catch (HibernateException e) {
            LOG.error("Error load() " + getPersistentClass() + " in IDao" + e);
            throw new DaoException(e);
        }
        return t;
    }

    public void delete(T t) throws DaoException {
        try {
            Session session = HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
            session.delete(t);
            LOG.info("Delete:" + t);
        } catch (HibernateException e) {
            LOG.error("Error save or update in IDao" + e);
            throw new DaoException(e);
        }
    }

    public List<T> getAll() throws DaoException {
        Session session = HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
        try {
            return session.createCriteria(getPersistentClass()).setCacheable(true).list();
        }catch (HibernateException e){
            LOG.error(""+e);
            throw  new DaoException(e);
        }
    }

    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
