package by.it.pvt.du4;

import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.dao.DaoFactory;
import by.it.pvt.du4.dao.RoleDAO;
import by.it.pvt.du4.dao.UserDAO;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.exceptions.ServiceException;
import by.it.pvt.du4.exceptions.ValidationException;
import by.it.pvt.du4.util.HibernateUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class UserService extends BaseService<User>{
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private static volatile UserService instance;
    static final String salt = "airport";

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
        Session session = HibernateUtil.getHibernateUtil().getHibernateSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            user.setUpdatedDate(new Date());
            User user1 = UserDAO.getInstance().get(user.getId());
            user1.setEmail(user.getEmail());
            user1.setLogin(user.getLogin());
            user1.setRole(user.getRole());
            user1.setUpdatedDate(user.getUpdatedDate());
            UserDAO.getInstance().saveOrUpdate(user1);
            t.commit();
            session.flush();
        }catch (Exception e) {
            t.rollback();
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public void create(User user) throws ServiceException {
        user.setPass(DigestUtils.md5Hex(salt + user.getPass()));
        try {
            HibernateUtil util =  HibernateUtil.getHibernateUtil();
            Session session = util.getHibernateSession();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Role role = RoleDAO.getInstance().getByName("user");
                user.setRole(role);
                DaoFactory.getInstance().getDao(UserDAO.class).saveOrUpdate(user);
                UserDAO.getInstance().saveOrUpdate(user);
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

        Session session = HibernateUtil.getHibernateUtil().getHibernateSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            user.setPass(DigestUtils.md5Hex(salt + user.getPass()));
            user = UserDAO.getInstance().getByLoginAndPassword(user);
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
        Session session = HibernateUtil.getHibernateUtil().getHibernateSession();
        Transaction t = null;
        List<User> users = null;
        try {
            t = session.beginTransaction();
            users = UserDAO.getInstance().getAll();
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
