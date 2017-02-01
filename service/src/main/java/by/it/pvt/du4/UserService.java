package by.it.pvt.du4;

import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.dao.DAO;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.exceptions.ServiceException;
import by.it.pvt.du4.exceptions.ValidationException;
import by.it.pvt.du4.util.HibernateUtil;
import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private static volatile UserService instance;
    static final String solt = "airport";

    private UserService() {
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

    public void update(User user) throws ServiceException {
        try {
            DAO.getDAO().userDAO.saveOrUpdate(user);
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }

    }

    public void delete(User user) throws ServiceException {
        try {
            DAO.getDAO().userDAO.delete(user);
        } catch (DaoException e) {
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
                Criteria criteria = session.createCriteria(Role.class);
                criteria.add(Restrictions.eq("name", "user"));
                List<Role>results = criteria.list();
                if (results.size() != 1){
                    throw new DaoException("Can't find user role");
                }
                user.setRole(results.get(0));
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

    public User get(User user) throws ValidationException, ServiceException {
        if (user.getLogin().length() > 50){
            throw new ValidationException(new IllegalArgumentException("User login to long"));
        }

        if (user.getPass().length() > 50){
            throw new ValidationException(new IllegalArgumentException("User password to long"));
        }

        try {
            return DAO.getDAO().userDAO.get(user.getId());
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }
}
