package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDAO")
public class UserDAO  extends BaseDao <User> implements IUserDao{
    private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);

    @Autowired
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User getByLoginAndPassword(User user) throws DaoException {
        LOG.info("Get user by login:" + user.getLogin()+ " and pass");
        try {
//            Session session = HibernateUtil.getHibernateUtil().getHibernateSession();
            Criterion login = Restrictions.eq("login", user.getLogin());
            Criterion pass = Restrictions.eq("pass",user.getPass());
            List<User> users =  getSession().createCriteria(User.class).add(Restrictions.and(login, pass)).list();
            if (users.size() == 1){
                return users.get(0);
            }else throw  new DaoException("No user matches query");
        } catch (HibernateException e) {
            LOG.error("" + e);
            throw new DaoException(e);
        }
    }
}
