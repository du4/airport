package by.it.pvt.du4.dao;


import by.it.pvt.du4.beans.*;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;

public class FlightDAO extends BaseDao <Flight> {
    private static final Logger LOG = LoggerFactory.getLogger(FlightDAO.class);

    public List<FlightStr> getFindByFilter(Map<String, String> flightQuery) throws DaoException {
        List<FlightStr> flights = new ArrayList<>();

        try {
            Session session = HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
            String hqlString = "SELECT f.id as q, f.flightCode, f.company, f.departure_time, f.arrival_time," +
                    "p.planeName , a.acronim, b.acronim,  u.login, f.createDate " +
                    "FROM Flight f " +
                    "inner join f.to_id  a " +
                    "inner join f.from_id  b " +
                    "inner join f.plane_id  p " +
                    "inner join f.user_id  u ";
            Query q = session.createQuery(hqlString);
            List<Object[]> res  = q.list();
            for (Object[] f : res) {
                flights.add(new FlightStr((Long)f[0],(String )f[1],(String )f[2],(Date)f[3],(Date)f[4],
                        (String)f[5],(String)f[6],(String)f[7],(String)f[8],(Date)f[9], null));
            }
            for (FlightStr f : flights) {
                q = session.createQuery("SELECT f.employees FROM Flight f WHERE f.id="+f.getId());
                List<Employee> crew  = q.list();
                f.setCrew(new HashSet<>(crew));
            }
            LOG.trace("Reading flights.");
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("" + e);
            throw new DaoException(e);
        }
        return flights;
    }

    public List<Employee> getFlightCrew(Serializable id) throws DaoException {
        try {
            Session session = HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
            Query q = session.createQuery("SELECT  f.employees FROM Flight f WHERE f.id="+id);
            List<Employee> crew = q.list();
            return crew;
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("" + e);
            throw new DaoException(e);
        }
    }

}