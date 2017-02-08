package by.it.pvt.du4;

import by.it.pvt.du4.beans.*;
import by.it.pvt.du4.dao.*;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.exceptions.ServiceException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.Transaction;
import org.hibernate.sql.ordering.antlr.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DictionaryServiceUtil {
    public static volatile DictionaryServiceUtil instance;
    private static final Logger LOG = LoggerFactory.getLogger(DictionaryServiceUtil.class);

    private DictionaryServiceUtil(){
    }

    public static DictionaryServiceUtil getInstance() {
        DictionaryServiceUtil localInstance = instance;
        if (localInstance == null) {
            synchronized (DictionaryServiceUtil.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DictionaryServiceUtil();
                }
            }
        }
        return localInstance;
    }

    public List<Role> getRoles() throws ServiceException {
        Transaction t = null;
        try {
            t = HibernateUtil.getHibernateUtil().getHibernateSession().beginTransaction();
            List<Role> roles = DaoFactory.getInstance().getDao(RoleDAO.class).getAll();
            t.commit();
            HibernateUtil.getHibernateUtil().getHibernateSession().flush();
            return roles;
        } catch (DaoException e) {
            LOG.error(""+e);
            t.rollback();
            throw new ServiceException(e);
        }
    }

    public List<Pilot> getPilots() throws ServiceException {
        Transaction t = null;
        try {
            t = HibernateUtil.getHibernateUtil().getHibernateSession().beginTransaction();
            List<Pilot> pilots = DaoFactory.getInstance().getDao(PilotDAO.class).getAll();
            t.commit();
            HibernateUtil.getHibernateUtil().getHibernateSession().flush();
            return pilots;
        } catch (DaoException e) {
            t.rollback();
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public List<Airport> getAirports() throws ServiceException {
        try {
            List<Airport>airports = new ArrayList<>();
            DaoFactory.getInstance().getDao(AirportsDAO.class).getAll().forEach(airport ->
                    airports.add(new Airport(airport.getId(), airport.getAcronim(),airport.getName(),null,null)));
            return airports;
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }
    public List<Airhostess> getAirhostesses() throws ServiceException {
        try {
            return DaoFactory.getInstance().getDao(AirhostessDAO.class).getAll();
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public List<Plane> getPlanes() throws ServiceException {
        try {
            List<Plane> planes = DaoFactory.getInstance().getDao(PlaneDAO.class).getAll();
            return planes;
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public List<Command> getCommands() throws ServiceException {
        try {
            List<Command>cmds = DaoFactory.getInstance().getDao(CommandDAO.class).getAll();
            return cmds;
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public List<Permission> getPermissions() throws ServiceException {
        try {
            List<Permission> pms = DaoFactory.getInstance().getDao(PermissionDAO.class).getAll();
            return pms;
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }
}
