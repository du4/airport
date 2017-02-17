package by.it.pvt.du4.service;

import by.it.pvt.du4.beans.Employee;
import by.it.pvt.du4.beans.Flight;
import by.it.pvt.du4.beans.FlightStr;
import by.it.pvt.du4.dao.IFlightDao;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.service.exceptions.ServiceException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FlightService extends BaseService<Flight> implements IFlightService {
    private static final Logger LOG = LoggerFactory.getLogger(FlightService.class);

    @Autowired
    IFlightDao flightDao;


    public List<Employee> gerFlightCrew(Serializable id) throws ServiceException {
        Session session = HibernateUtil.getHibernateUtil().getHibernateSession();
        Transaction t = null;
        List<Employee> crew = null;
        try {
            t = session.beginTransaction();
            crew = flightDao.getFlightCrew(id);
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
            return flightDao.getByFilter(flightQuery);
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public Long getFlightsCount() throws ServiceException {
        try {
            return flightDao.getCount();
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }
}
