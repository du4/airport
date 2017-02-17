package by.it.pvt.du4.dao;


import by.it.pvt.du4.beans.Airhostess;
import by.it.pvt.du4.beans.Employee;
import by.it.pvt.du4.beans.Pilot;
import by.it.pvt.du4.dao.exceptions.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AirhostessDAO extends BaseDao <Airhostess>  implements IAirhostessDao{
    private static Logger log = LoggerFactory.getLogger(Airhostess.class);

    @Autowired
    public AirhostessDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Airhostess> getAll(Class<Airhostess> clazz) throws DaoException {
        try {
            return getSession().createCriteria(Employee.class).add(Restrictions.eq("class","airhostess")).list();
        }catch (HibernateException e){
            log.error(""+e);
            throw  new DaoException(e);
        }
    }
}
