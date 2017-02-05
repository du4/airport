package by.it.pvt.du4;

import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.dao.DAO;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.exceptions.ServiceException;
import by.it.pvt.du4.exceptions.ValidationException;
import by.it.pvt.du4.util.HibernateUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserService implements IService<User>{
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private static volatile UserService instance;
    static final String solt = "airport";


    private UserService()  {
    }

    public static  UserService getInstance(){
        if (instance == null) {
            synchronized (UserService.class) {
                if(instance == null){
                    instance = new UserService();
                }
            }
        }
        return instance;
    }

    @Override
    public void saveOrUpdate(User user) throws ServiceException {
        Session session = HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            user.setUpdatedDate(new Date());
            DAO.getDAO().userDAO.saveOrUpdate(user);
            t.commit();
            session.flush();
        }catch (Exception e) {
            t.rollback();
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    @Override
    public User getById(Serializable id) throws ServiceException {
        Session session = HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
        Transaction t = null;
        User user = null;
        try {
            t = session.beginTransaction();
            user = DAO.getDAO().userDAO.get(id);
            t.commit();
            session.flush();
            return  user;
        }catch (Exception e) {
            t.rollback();
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    @Override
    public User loadById(Serializable id) throws ServiceException {
        Session session = HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
        Transaction t = null;
        User user = null;
        try {
            t = session.beginTransaction();
            user = DAO.getDAO().userDAO.load(id);
            t.commit();
            session.flush();
            return  user;
        }catch (Exception e) {
            t.rollback();
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public void delete(User user) throws ServiceException {
        Session session = HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            DAO.getDAO().userDAO.delete(user);
            t.commit();
            session.flush();
        } catch (DaoException e) {
            t.rollback();
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public void create(User user) throws ServiceException {
        user.setPass(DigestUtils.md5Hex(solt + user.getPass()));
        try {
            HibernateUtil util =  HibernateUtil.getHibernateUtil();
            Session session = util.getSessionFromThreadLocal();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Role role = DAO.getDAO().roleDAO.getByName("user");
                user.setRole(role);
                DAO.getDAO().userDAO.saveOrUpdate(user);
                transaction.commit();
                session.flush();
            }catch (HibernateException e) {
                transaction.rollback();
                LOG.error(""+e);
                throw new DaoException(e);
            }
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public User getByLoginAndPassword(User user) throws ValidationException, ServiceException {
        if (user.getLogin().length() > 50){
            throw new ValidationException(new IllegalArgumentException("User login to long"));
        }

        if (user.getPass().length() > 50){
            throw new ValidationException(new IllegalArgumentException("User password to long"));
        }

        Session session = HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            user.setPass(DigestUtils.md5Hex(solt + user.getPass()));
            user = DAO.getDAO().userDAO.getByLoginAndPassword(user);
            t.commit();
            session.flush();
            return user;
        } catch (DaoException e) {
            t.rollback();
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public List<User> getAll() throws ServiceException {
        Session session = HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
        Transaction t = null;
        List<User> users = null;
        try {
            t = session.beginTransaction();
            users = DAO.getDAO().userDAO.getAll();
            t.commit();
            session.flush();
            return users;
        }catch (Exception e) {
            t.rollback();
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }


}
