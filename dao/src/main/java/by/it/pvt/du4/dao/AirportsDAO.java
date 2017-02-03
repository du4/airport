package by.it.pvt.du4.dao;


import by.it.pvt.du4.beans.Airport;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AirportsDAO extends BaseDao <Airport> {
    private static final Logger LOG = LoggerFactory.getLogger(AirportsDAO.class);

    public List<Airport> getAll() throws DaoException {
        Session session = HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
        try {
            return   session.createCriteria(Airport.class).setCacheable(true).list();
        }catch (HibernateException e){
            LOG.error(""+e);
            throw  new DaoException(e);
        }
    }
}
