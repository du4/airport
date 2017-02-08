package by.it.pvt.du4;

import by.it.pvt.du4.beans.*;
import by.it.pvt.du4.dao.DaoFactory;
import by.it.pvt.du4.dao.FlightDAO;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.exceptions.ServiceException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FlightService implements IService<Flight> {
    private static volatile FlightService instance;
    private static final Logger LOG = LoggerFactory.getLogger(FlightService.class);

    private FlightService() {
    }

    public static FlightService getInstance() {
        if (instance == null) {
            synchronized (FlightService.class){
                if (instance == null){
                    instance = new FlightService();
                }
            }
        }
        return instance;
    }

    @Override
    public void saveOrUpdate(Flight flight) throws ServiceException {
        try {
            DaoFactory.getInstance().getDao(FlightDAO.class).saveOrUpdate(flight);
        } catch (DaoException e) {
            e.printStackTrace();
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Flight get(Serializable id) throws ServiceException {
        return null;
    }

    @Override
    public Flight load(Serializable id) throws ServiceException {
        return null;
    }

    @Override
    public void delete(Flight flight) throws ServiceException {

    }

    public List<Employee> gerFlightCrew(Serializable id) throws ServiceException {
        Session session = HibernateUtil.getHibernateUtil().getHibernateSession();
        Transaction t = null;
        List<Employee> crew = null;
        try {
            t = session.beginTransaction();
            crew = DaoFactory.getInstance().getDao(FlightDAO.class).getFlightCrew(id);
            t.commit();
            session.flush();
            return  crew;
        }catch (Exception e) {
            assert t != null;
            t.rollback();
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public List<FlightStr> getAllStringFlights(Map<String,String> flightQuery) throws ServiceException {
        try {
            return DaoFactory.getInstance().getDao(FlightDAO.class).getByFilter(flightQuery);
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public Long getFlightsCount() throws ServiceException {
        try {
            return DaoFactory.getInstance().getDao(FlightDAO.class).getCount();
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }
}
