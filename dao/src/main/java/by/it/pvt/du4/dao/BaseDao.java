package by.it.pvt.du4.dao;


import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public class BaseDao<T> implements IDao<T> {
    private static final Logger LOG = LoggerFactory.getLogger(BaseDao.class);
    private SessionFactory sessionFactory;
    private Session session;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public BaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.session = sessionFactory.openSession();
    }

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void update(T t) throws DaoException{
        try {
            session.update(t);
            session.flush();
            LOG.info("update(t):" + t);
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("Error save or update in IDao" + e);
            throw new DaoException(e);
        }
    }
    @Override
    public T get(Class<T>clazz, Serializable id) throws DaoException {
        LOG.info("Get class by id:" + id);
        T t = null;
        try {
            Session session = HibernateUtil.getHibernateUtil().getHibernateSession();
            t = (T) session.get(clazz, id);
            LOG.info("get clazz:" + t);
        } catch (HibernateException e) {
            LOG.error("Error get " + clazz + " in IDao" + e);
            throw new DaoException(e);
        }
        return t;
    }
    @Override
    public void create(T t) throws DaoException {
        try {
            session.saveOrUpdate(t);
            LOG.info("update(t):" + t);
            session.flush();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("Error save or update in IDao" + e);
            throw new DaoException(e);
        }
    }
    @Override
    public void delete(T t) throws DaoException {
        try {
            session.delete(t);
            LOG.info("Delete:" + t);
            session.flush();
        } catch (HibernateException e) {
            LOG.error("Error save or update in IDao" + e);
            throw new DaoException(e);
        }
    }
    @Override
    public List<T> getAll(Class<T>clazz) throws DaoException {
        try {
            return session.createCriteria(clazz).setCacheable(true).list();
        }catch (HibernateException e){
            LOG.error(""+e);
            throw  new DaoException(e);
        }
    }
}
