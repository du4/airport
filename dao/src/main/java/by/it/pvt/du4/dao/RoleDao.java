package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.dao.exceptions.DaoException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDao extends BaseDao <Role> implements IRoleDao{

    private static final Logger LOG = LoggerFactory.getLogger(RoleDao.class);

    @Autowired
    public RoleDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Role getByName(String name) throws DaoException {

        try {
//            Session session = HibernateUtil.getHibernateUtil().getHibernateSession();
            Criteria criteria = getSession().createCriteria(Role.class).add(Restrictions.eq("name", name));
            List<Role> result =  criteria.list();
            LOG.info("Get Role by name:" + name);
            return  result.get(0);
        } catch (HibernateException e) {
            LOG.error("Error get Role " + e);
            throw new DaoException(e);
        }
    }



}
