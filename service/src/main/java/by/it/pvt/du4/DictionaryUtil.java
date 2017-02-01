package by.it.pvt.du4;

import by.it.pvt.du4.beans.*;
import by.it.pvt.du4.dao.DAO;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DictionaryUtil {
    public static volatile DictionaryUtil instance;
    private static final Logger LOG = LoggerFactory.getLogger(DictionaryUtil.class);

    private DictionaryUtil(){
    }

    public static DictionaryUtil getInstance() {
        DictionaryUtil localInstance = instance;
        if (localInstance == null) {
            synchronized (DictionaryUtil.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DictionaryUtil();
                }
            }
        }
        return localInstance;
    }

    public List<Role> getRoles() throws ServiceException {
        try {
            return DAO.getDAO().roleDAO.getAll();
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public List<Pilot> getPilots() throws ServiceException {
        try {
            return DAO.getDAO().pilotDAO.getAll();
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public List<Airport> getAirports() throws ServiceException {
        try {
            return DAO.getDAO().airportsDAO.getAll();
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


    public List<User> getUsers() throws ServiceException {
        try {
            return DAO.getDAO().userDAO.getAll();
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public List<Plane> getPlanes() throws ServiceException {
        try {
            return DAO.getDAO().planeDAO.getAll();
        } catch (DaoException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
    }

    public List<Crew> getCrews() throws ServiceException {
        try {
            return DAO.getDAO().crewDAO.getAll();
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
