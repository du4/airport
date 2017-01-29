package by.it.pvt.du4;

import by.it.pvt.du4.beans.Airport;
import by.it.pvt.du4.dao.DAO;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class FlightService {
    private static volatile FlightService instance;
    private static final Logger LOG = LoggerFactory.getLogger(FlightService.class);

    private FlightService() {
    }

    public static FlightService gerInstance() {
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


//
//    public List<FlightStr> getAll(String flightQuery) throws ServiceException {
//        try {
//            return DAO.getDAO().flightStrDAO.getAll(flightQuery);
//        } catch (DaoException e) {
//            LOG.error(""+e);
//            throw new ServiceException(e);
//        }
//    }
//
//    public int create(Flight flight) throws ServiceException {
//        try {
//            return DAO.getDAO().flightDAO.create(flight);
//        } catch (DaoException e) {
//            LOG.error(""+e);
//            throw new ServiceException(e);
//        }
//    }
}
