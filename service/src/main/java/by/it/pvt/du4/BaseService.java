package by.it.pvt.du4;

import by.it.pvt.du4.dao.BaseDao;
import by.it.pvt.du4.dao.DaoFactory;
import by.it.pvt.du4.dao.UserDAO;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.exceptions.ServiceException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class BaseService<T> implements IService<T> {
    private static final Logger LOG = LoggerFactory.getLogger(BaseService.class);

    public BaseService() {
    }

    @Override
    public void saveOrUpdate(T entity) throws ServiceException {
//        Session session = HibernateUtil.getHibernateUtil().getHibernateSession();
//        Transaction t = null;
//        try {
//            t = session.beginTransaction();
//            DaoFactory.getInstance().getDao(UserDAO.class).saveOrUpdate(entity);
//            t.commit();
//            session.flush();
//        }catch (Exception e) {
//            t.rollback();
//            LOG.error(""+e);
//            throw new ServiceException(e);
//        }
    }

    @Override
    public T get(Serializable id) throws ServiceException {
//        LOG.info("Get class by id:" + id);
//        T entity = null;
//        Session session = HibernateUtil.getHibernateUtil().getHibernateSession();
//        Transaction t = null;
//        try {
//            t = session.beginTransaction();
//            entity = (T) BaseDao.getInstance().get(id);
//            t.commit();
//            session.flush();
//            return  entity;
//        }catch (Exception e) {
//            t.rollback();
//            LOG.error(""+e);
//            throw new ServiceException(e);
//        }
        return  null;
    }

    @Override
    public T load(Serializable id) throws ServiceException {
//        LOG.info("Load class by id:" + id);
//        T entity = null;
//        Session session = HibernateUtil.getHibernateUtil().getHibernateSession();
//        Transaction t = null;
//        try {
//            t = session.beginTransaction();
//            entity = (T) BaseDao.getInstance().load(id);
//            t.commit();
//            session.flush();
//            return  entity;
//        }catch (Exception e) {
//            t.rollback();
//            LOG.error(""+e);
//            throw new ServiceException(e);
//        }
        return null;
    }

    @Override
    public void delete(T entity) throws ServiceException {
//        Session session = HibernateUtil.getHibernateUtil().getHibernateSession();
//        Transaction t = null;
//        try {
//            t = session.beginTransaction();
//            BaseDao.getInstance().delete(entity);
//            t.commit();
//            session.flush();
//        } catch (DaoException e) {
//            t.rollback();
//            LOG.error(""+e);
//            throw new ServiceException(e);
//        }
    }
}
