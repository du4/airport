package by.it.pvt.du4;

import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.dao.DAO;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.exceptions.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
//        user.setPass(DigestUtils.md5Hex(user.getPass()));
        try {
            return DAO.getDAO().userDAO.create(user);
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public List<User> get(User user) throws ServiceException {
        if (user.getLogin().length() > 30){
            throw new ServiceException(new IllegalArgumentException("User login to long"));
        }

        if (user.getPass().length() > 30){
            throw new ServiceException(new IllegalArgumentException("User password to long"));
        }

        try {
            String str = "WHERE login='" + user.getLogin() +"' AND pass='" + user.getPass() + "'";
            return DAO.getDAO().userDAO.getAll(str);
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }
}
