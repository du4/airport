package by.it.pvt.du4.dao;


import by.it.pvt.du4.beans.Flight;
import by.it.pvt.du4.beans.FlightStr;
import by.it.pvt.du4.beans.Plane;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class FlightDAO extends BaseDao <Flight> {
    private static final Logger LOG = LoggerFactory.getLogger(CrewDAO.class);

    public List<FlightStr> getFindByFilter(Map<String, String> flightQuery) throws DaoException {
        List<FlightStr> flights = new ArrayList<>();

        try {
            Session session = HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
            String hqlString = "SELECT f.id, f.flightCode, f.company, f.departure_time, f.arrival_time, " +
                    "p.planeName , a.acronim, b.acronim,  u.login, f.createDate " +
                    "FROM Flight f " +
                    "inner join f.to_id  a " +
                    "inner join f.from_id  b " +
                    "inner join f.plane_id  p " +
                    "inner join f.user_id  u ";

            Query q = session.createQuery(hqlString);
            List<Object[]> res  = q.list();
            for (Object[] f : res) {
                flights.add(new FlightStr((Long)f[0],(String )f[1],(String )f[2],(Date)f[3],(Date)f[4],(String)f[5],(String)f[6],(String)f[7],"crewX",(String)f[8],(Date)f[9]));
            }
            session.createCriteria(Plane.class).setCacheable(true).list();
        } catch (HibernateException e) {
            LOG.error("" + e);
            throw new DaoException(e);
        }
        return flights;
    }

}