package by.it.pvt.du4;

import by.it.pvt.du4.beans.*;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.exceptions.ServiceException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceDataGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceDataGenerator.class);
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private static volatile ServiceDataGenerator instance;

    private ServiceDataGenerator() {
    }

    public static  ServiceDataGenerator getInstance(){
        if (instance == null) {
            synchronized (ServiceDataGenerator.class) {
                if(instance == null){
                    instance = new ServiceDataGenerator();
                }
            }
        }
        return instance;
    }

    public void generateData() throws DaoException, ServiceException {
        generateRolesAndUsers();
        generateAirports();
        generateCommandsPermissions();
        generatePlanes();
        generatePilots();
        generateAirhostesses();
//        closeHibernateSession();
    }

    private void generateRolesAndUsers() throws DaoException {
        Session session =  HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
        Transaction transaction = null;
        List<Role>roles = new ArrayList<Role>(){{
                add(new Role("admin"));
                add(new Role("dispatcher"));
                add(new Role("user"));
            }};
        saveCollection(session, transaction, roles);
        List<User>users = new ArrayList<User>(){{
                add(new User(null, "admin", "admin@airport.by", "admin", roles.get(0), new Date(), null));
                add(new User(null, "dispatcher", "dispatcher@airport.by", "dispatcher", roles.get(1), new Date(), null));
                add(new User(null, "user", "user@airport.by", "user", roles.get(2), new Date(), null));
            }};
        saveCollection(session, transaction, users);
    }

    private void generateAirports() throws DaoException {
        Session session =  HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
        Transaction transaction = null;
        List<Airport>airports = new ArrayList<Airport>(){{
                add(new Airport("MSQ", "Minsk-2"));
                add(new Airport("VIE", "Vienna"));
                add(new Airport("TGD", "Golubovichi"));
                add(new Airport("SVO", "Sheremetyevo"));
                add(new Airport("BKA", "Bukavo"));
            }};
        saveCollection(session, transaction, airports);
    }

    private void generatePlanes() throws DaoException {
        Session session =  HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
        Transaction transaction = null;
        List<Plane>planes = new ArrayList<Plane>(){{
            add(new Plane("Boeing-747"));
            add(new Plane("Boeing-727"));
            add(new Plane("Boeing 737-500"));
            add(new Plane("Airbus A320"));
            add(new Plane("Gulfstream 650"));
            add(new Plane("Embraer EMB190"));

        }};
        saveCollection(session, transaction, planes);
    }

    private void generatePilots() throws DaoException, ServiceException {
        Session session =  HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
        Transaction transaction = null;
        List<Pilot>pilots = new ArrayList<>();
        try{
            pilots.add(new Pilot("Dubatovka Sergey","375298784275",  formatter.parse("1983-04-14")));
            pilots.add(new Pilot("Averuk Evgeniy","37529673457441",  formatter.parse("1981-11-07")));
            pilots.add(new Pilot("Vasiliev Andrew","375291234567",  formatter.parse("1982-05-31")));
        } catch (ParseException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
        saveCollection(session, transaction, pilots);
    }

    private void generateAirhostesses() throws DaoException, ServiceException {
        Session session =  HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
        Transaction transaction = null;
        List<Airhostess>airhostesses = new ArrayList<>();
        try{
            airhostesses.add(new Airhostess("Dzyachkova Inha",formatter.parse("1981-12-31")));
            airhostesses.add(new Airhostess("Zhravkova Stefania",formatter.parse("1991-10-30")));
            airhostesses.add(new Airhostess("Kubeko Diana",formatter.parse("1982-06-10")));
            airhostesses.add(new Airhostess("Golovko Olha",formatter.parse("1988-05-11")));
        } catch (ParseException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
        saveCollection(session, transaction, airhostesses);
    }

    private void generateCommandsPermissions() throws DaoException {
        Session session =  HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
        Transaction transaction = null;
        List<Command>commands = new ArrayList<Command>(){{
            add(new Command("index"));
            add(new Command("login"));
            add(new Command("logout"));
            add(new Command("signup"));
            add(new Command("error"));
            add(new Command("usermanagement"));
            add(new Command("stuffingcrew"));
            add(new Command("newflight"));
            add(new Command("profile"));
        }};
        saveCollection(session, transaction, commands);
    }

    private <T> void  saveCollection(Session session, Transaction transaction, List<T> list) throws DaoException {
        try {
            transaction = session.beginTransaction();
            list.forEach(e -> session.saveOrUpdate(e));
            transaction.commit();
            session.flush();
        }catch (HibernateException e){
            transaction.rollback();
            LOG.error(""+e);
            throw new DaoException(e);
        }
    }

    private void closeHibernateSession(){
        HibernateUtil.getHibernateUtil().getSessionFromThreadLocal().close();
    }

}
