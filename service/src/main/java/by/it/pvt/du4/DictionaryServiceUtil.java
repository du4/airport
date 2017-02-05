package by.it.pvt.du4;

import by.it.pvt.du4.beans.*;
import by.it.pvt.du4.dao.DAO;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.exceptions.ServiceException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
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
            t = HibernateUtil.getHibernateUtil().getSessionFromThreadLocal().beginTransaction();
            List<Role> roles =  DAO.getDAO().roleDAO.getAll();
            t.commit();
            HibernateUtil.getHibernateUtil().getSessionFromThreadLocal().flush();
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
            t = HibernateUtil.getHibernateUtil().getSessionFromThreadLocal().beginTransaction();
            List<Pilot> pilots =  DAO.getDAO().pilotDAO.getAll();
            t.commit();
            HibernateUtil.getHibernateUtil().getSessionFromThreadLocal().flush();
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
            DAO.getDAO().airportsDAO.getAll().forEach(airport -> airports.add(new Airport(airport.getId(), airport.getAcronim(),airport.getName(),null,null)));
            return airports;
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }
    public List<Airhostess> getAirhostesses() throws ServiceException {
        try {
            return DAO.getDAO().airhostessDAO.getAll();
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public List<Plane> getPlanes() throws ServiceException {
        try {
            List<Plane> planes = new ArrayList<>();
            DAO.getDAO().planeDAO.getAll().forEach(plane -> planes.add(new Plane(plane.getId(), plane.getPlaneName(),null)));
            return planes;
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public List<Command> getCommands() throws ServiceException {
        try {
            List<Command>cmds = new ArrayList<>();
            List<Command> commands = DAO.getDAO().commandDAO.getAll();
            commands.forEach(cmd -> cmds.add(new Command(cmd.getId(),cmd.getName(), cmd.getPermissions())));
            return cmds;
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public List<Permission> getPermissions() throws ServiceException {
        try {
            List<Permission> pms = new ArrayList<>();
            List<Permission> permissions =  DAO.getDAO().permissionDAO.getAll();
            permissions.forEach(prm->pms.add(new Permission(prm.getId(), prm.getRole_id(), prm.getCommand_id(), prm.getPermission())));
            return pms;
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }
}
