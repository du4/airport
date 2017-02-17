package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Employee;
import by.it.pvt.du4.beans.Pilot;
import by.it.pvt.du4.beans.Plane;
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
public class PilotDao extends BaseDao <Pilot> implements IPilotDao{
    private static Logger log = LoggerFactory.getLogger(Pilot.class);
    @Autowired
    public PilotDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Pilot> getAll(Class<Pilot> clazz) throws DaoException{

        try {
            return getSession().createCriteria(Employee.class).add(Restrictions.eq("class","pilot")).list();
        }catch (HibernateException e){
            log.error(""+e);
            throw  new DaoException(e);
        }
    }
}
