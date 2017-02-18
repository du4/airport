package by.it.pvt.du4.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DictionaryServiceUtil {
    private static final Logger LOG = LoggerFactory.getLogger(DictionaryServiceUtil.class);

    public DictionaryServiceUtil(){
    }


//
//    public List<Role> getRoles() throws ServiceException {
//        Transaction t = null;
//        try {
//            t = HibernateUtil.getHibernateUtil().getHibernateSession().beginTransaction();
//            List<Role> roles = DaoFactory.getInstance().getDao(RoleDAO.class).getAll(Role.class);
//            t.commit();
//            HibernateUtil.getHibernateUtil().getHibernateSession().flush();
//            return roles;
//        } catch (DaoException e) {
//            LOG.error(""+e);
//            t.rollback();
//            throw new ServiceException(e);
//        }
//    }
//
//    public List<Pilot> getPilots() throws ServiceException {
//        Transaction t = null;
//        try {
//            t = HibernateUtil.getHibernateUtil().getHibernateSession().beginTransaction();
//            List<Pilot> pilots = DaoFactory.getInstance().getDao(PilotDAO.class).getAll(Pilot.class);
//            t.commit();
//            HibernateUtil.getHibernateUtil().getHibernateSession().flush();
//            return pilots;
//        } catch (DaoException e) {
//            t.rollback();
//            LOG.error(""+e);
//            throw new ServiceException(e);
//        }
//    }
//
//    public List<Airport> getAirports() throws ServiceException {
//        try {
//            List<Airport>airports = new ArrayList<>();
//            DaoFactory.getInstance().getDao(AirportsDAO.class).getAll(Airport.class).forEach(airport ->
//                    airports.add(new Airport(airport.getId(), airport.getAcronim(),airport.getName(),null,null)));
//            return airports;
//        } catch (DaoException e) {
//            LOG.error(""+e);
//            throw new ServiceException(e);
//        }
//    }
//    public List<Airhostess> getAirhostesses() throws ServiceException {
//        try {
//            return DaoFactory.getInstance().getDao(AirhostessDAO.class).getAll(Airhostess.class);
//        } catch (DaoException e) {
//            LOG.error(""+e);
//            throw new ServiceException(e);
//        }
//    }
//
//    public List<Plane> getPlanes() throws ServiceException {
//        try {
//            List<Plane> planes = DaoFactory.getInstance().getDao(PlaneDAO.class).getAll(Plane.class);
//            return planes;
//        } catch (DaoException e) {
//            LOG.error(""+e);
//            throw new ServiceException(e);
//        }
//    }
//
//    public List<Command> getCommands() throws ServiceException {
//        try {
//            List<Command>cmds = DaoFactory.getInstance().getDao(CommandDAO.class).getAll(Command.class);
//            return cmds;
//        } catch (DaoException e) {
//            LOG.error(""+e);
//            throw new ServiceException(e);
//        }
//    }
//
//    public List<Permission> getPermissions() throws ServiceException {
//        try {
//            List<Permission> pms = DaoFactory.getInstance().getDao(PermissionDAO.class).getAll(Permission.class);
//            return pms;
//        } catch (DaoException e) {
//            LOG.error(""+e);
//            throw new ServiceException(e);
//        }
//    }
}
