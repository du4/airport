package by.it.pvt.du4.dao;


import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;


public class BaseDao<T> implements Dao<T> {
    private static final Logger LOG = LoggerFactory.getLogger(BaseDao.class);
    private Transaction transaction = null;


    public BaseDao() {

    }

    public void saveOrUpdate(T t) throws DaoException{
        try {
            Session session = HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
//            transaction = session.beginTransaction();
            session.saveOrUpdate(t);
            LOG.info("saveOrUpdate(t):" + t);
//            transaction.commit();
//            LOG.info("Save or update (commit):" + t);
        } catch (HibernateException e) {
//            LOG.error("Error save or update PERSON in Dao" + e);
//            transaction.rollback();
//            throw new DaoException(e);
        }

    }

    public T get(Serializable id) throws DaoException {
        LOG.info("Get class by id:" + id);
        T t = null;
        try {
            Session session = HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
            transaction = session.beginTransaction();
            t = (T) session.get(getPersistentClass(), id);
            transaction.commit();
            LOG.info("get clazz:" + t);
        } catch (HibernateException e) {
            transaction.rollback();
            LOG.error("Error get " + getPersistentClass() + " in Dao" + e);
            throw new DaoException(e);
        }
        return t;
    }

    public T load(Serializable id) throws DaoException {
        LOG.info("Load class by id:" + id);
        T t = null;
        try {
            Session session = HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
            transaction = session.beginTransaction();
            t = (T) session.load(getPersistentClass(), id);
            LOG.info("load() clazz:" + t);
            session.isDirty();
            transaction.commit();
        } catch (HibernateException e) {
            LOG.error("Error load() " + getPersistentClass() + " in Dao" + e);
            transaction.rollback();
            throw new DaoException(e);
        }
        return t;
    }

    public void delete(T t) throws DaoException {
        try {
            Session session = HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
            transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
            LOG.info("Delete:" + t);
        } catch (HibernateException e) {
            LOG.error("Error save or update PERSON in Dao" + e);
            transaction.rollback();
            throw new DaoException(e);
        }
    }

    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
