package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RoleDAO extends BaseDao <Role> {
    private static final Logger LOG = LoggerFactory.getLogger(RoleDAO.class);
    private static volatile RoleDAO instance;

    private RoleDAO()  {
    }

    public static  RoleDAO getInstance(){
        if (instance == null) {
            synchronized (RoleDAO.class) {
                if(instance == null){
                    instance = new RoleDAO();
                }
            }
        }
        return instance;
    }

    public Role getByName(String name) throws DaoException {

        try {
            Session session = HibernateUtil.getHibernateUtil().getHibernateSession();
            Criteria criteria = session.createCriteria(Role.class).add(Restrictions.eq("name", name));
            List<Role> result =  criteria.list();
            LOG.info("Get Role by name:" + name);
            return  result.get(0);
        } catch (HibernateException e) {
            LOG.error("Error get Role " + e);
            throw new DaoException(e);
        }
    }



}
