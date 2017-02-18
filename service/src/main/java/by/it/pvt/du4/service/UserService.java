package by.it.pvt.du4.service;

import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.dao.*;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.service.exceptions.ServiceException;
import by.it.pvt.du4.service.exceptions.ValidationException;
import by.it.pvt.du4.service.interfaces.IUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserService extends BaseService<User>  implements IUserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private static final String salt = "airport";

    @Autowired
    IUserDao userDao;

    @Override
    public User getByLoginAndPassword(User user) throws ValidationException, ServiceException {
    if (user.getLogin().length() > 50){
        throw new ValidationException(new IllegalArgumentException("User login to long"));
    }

    if (user.getPass().length() > 50){
        throw new ValidationException(new IllegalArgumentException("User password to long"));
    }

    try {
        user.setPass(DigestUtils.md5Hex(salt + user.getPass()));
        user =  userDao.getByLoginAndPassword(user);
        return user;
    } catch (DaoException e) {
        LOG.error(""+e);
        throw new ServiceException(e);
    }
}

    public static String getSalt() {
        return salt;
    }
}
