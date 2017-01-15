package by.it.pvt.du4;

import by.it.pvt.du4.beans.Crew;
import by.it.pvt.du4.dao.DAO;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrewService {
    private static final Logger LOG = LoggerFactory.getLogger(CrewService.class);

    private static volatile CrewService instance;

    private CrewService(){

    }

    public static CrewService getInstance(){
        if (instance == null){
            synchronized (CrewService.class){
                if (instance == null){
                    instance = new CrewService();
                }
            }
        }
        return instance;
    }


    public int create(Crew crew) throws ServiceException {
        try {
            return DAO.getDAO().crewDAO.create(crew);
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }
}