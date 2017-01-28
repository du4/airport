package by.it.pvt.du4;

import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.dao.DAO;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.exceptions.ServiceException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private static volatile UserService instance;

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
            DAO.getDAO().userDAO.update(user);
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }

    }

    public boolean delete(User user) throws ServiceException {
        try {
            return DAO.getDAO().userDAO.delete(user);
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public int create(User user) throws ServiceException {
//        user_id.setPass(DigestUtils.md5Hex(user_id.getPass()));
        try {
            HibernateUtil util =  HibernateUtil.getHibernateUtil();
            Session session = util.getSessionFromThreadLocal();

            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.saveOrUpdate(user);
                transaction.commit();

                session.flush();
            }catch (HibernateException e) {
                transaction.rollback();
                LOG.error(""+e);
                throw new DaoException(e);
            }


            return DAO.getDAO().userDAO.create(user);
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public User get(User user) throws ServiceException {
        if (user.getLogin().length() > 30){
            throw new ServiceException(new IllegalArgumentException("User login to long"));
        }

        if (user.getPass().length() > 30){
            throw new ServiceException(new IllegalArgumentException("User password to long"));
        }

        try {
            return DAO.getDAO().userDAO.getByLogin(user.getLogin(),user.getPass());
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }
}
