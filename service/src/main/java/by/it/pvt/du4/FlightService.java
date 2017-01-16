package by.it.pvt.du4;

import by.it.pvt.du4.beans.Flight;
import by.it.pvt.du4.beans.FlightStr;
import by.it.pvt.du4.dao.DAO;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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



    public List<FlightStr> getAll(String flightQuery) throws ServiceException {
        try {
            return DAO.getDAO().flightStrDAO.getAll(flightQuery);
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public int create(Flight flight) throws ServiceException {
        try {
            return DAO.getDAO().flightDAO.create(flight);
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }
}
