package by.it.pvt.du4;

import by.it.pvt.du4.beans.Airport;
import by.it.pvt.du4.beans.Flight;
import by.it.pvt.du4.beans.FlightStr;
import by.it.pvt.du4.dao.DAO;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.exceptions.ServiceException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class FlightService {
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

    public List<Airport>  getAirports(){
        Session session =  HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
        Transaction transaction = null;
        List<Airport> airports = null;
        try{
//            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Airport.class);
            airports =  criteria.list();
        }catch (HibernateException e){
//            transaction.rollback();
            LOG.error(""+e);
        }
        return  airports;
    }


    public List<FlightStr> getAllStringFlights(Map<String,String> flightQuery) throws ServiceException {
        try {
            return DAO.getDAO().flightDAO.getFindByFilter(flightQuery);
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public void create(Flight flight) throws ServiceException {
        try {
            DAO.getDAO().flightDAO.saveOrUpdate(flight);
        } catch (DaoException e) {
            e.printStackTrace();
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }
}
