package by.it.pvt.du4;

import by.it.pvt.du4.beans.*;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.exceptions.ServiceException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        generateEmployees();
        generateCrews();
    }

    private void generateCrews() throws DaoException {
        Session session =  HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
        List<Pilot> pilots = session.createCriteria(Employee.class).add(Restrictions.eq("class","pilot")).list();
        List<Airhostess> airhostess = session.createCriteria(Employee.class).add(Restrictions.eq("class","airhostess")).list();

        Set<Employee> crew = new HashSet<Employee>(){{
            add(pilots.get(0)); add(pilots.get(1));
            add(airhostess.get(0));add(airhostess.get(1));
        }};
        List<Crew>crews = new ArrayList<>();
        crews.add(new Crew(null, crew, null));

        saveCollection(session, crews);
    }

    private void generateRolesAndUsers() throws DaoException {
        Session session =  HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
        List<Role>roles = new ArrayList<Role>(){{
                add(new Role("admin"));
                add(new Role("dispatcher"));
                add(new Role("user"));
            }};
        saveCollection(session, roles);
        List<User>users = new ArrayList<User>(){{
                add(new User(null, "admin", "admin@airport.by", "admin", roles.get(0), new Date(), null));
                add(new User(null, "dispatcher", "dispatcher@airport.by", "dispatcher", roles.get(1), new Date(), null));
                add(new User(null, "user", "user@airport.by", "user", roles.get(2), new Date(), null));
            }};
        saveCollection(session, users);
    }

    private void generateAirports() throws DaoException {
        Session session =  HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
        List<Airport>airports = new ArrayList<Airport>(){{
                add(new Airport("MSQ", "Minsk-2"));
                add(new Airport("VIE", "Vienna"));
                add(new Airport("TGD", "Golubovichi"));
                add(new Airport("SVO", "Sheremetyevo"));
                add(new Airport("BKA", "Bukavo"));
            }};
        saveCollection(session, airports);
    }

    private void generatePlanes() throws DaoException {
        Session session =  HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
        List<Plane>planes = new ArrayList<Plane>(){{
            add(new Plane("Boeing-747"));
            add(new Plane("Boeing-727"));
            add(new Plane("Boeing 737-500"));
            add(new Plane("Airbus A320"));
            add(new Plane("Gulfstream 650"));
            add(new Plane("Embraer EMB190"));

        }};
        saveCollection(session, planes);
    }

    private void generateEmployees() throws DaoException, ServiceException {
        Session session =  HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
        List<Employee>employees = new ArrayList<>();
        try{
            employees.add(new Employee(null, "Aristarh Nikodimych", "11111111", formatter.parse("1990-05-14"),formatter.parse("2016-04-14")));
            employees.add(new Pilot(null,"Dubatovka Sergey","375298784275",  formatter.parse("2003-04-14"), null , "II"));
            employees.add(new Pilot(null,"Averuk Evgeniy","37529673457441",  formatter.parse("2013-01-04"), null , "I"));
            employees.add(new Pilot(null,"Vasiliev Andrew","375291234567",  formatter.parse("1999-05-31"), null, "II"));
            employees.add(new Airhostess(null, "Dzyachkova Inha", "37529673457441",formatter.parse("2001-02-31"), null, formatter.parse("1981-12-31")));
            employees.add(new Airhostess(null, "Zhravkova Stefania", "37523273457441",formatter.parse("2011-02-01"), null,formatter.parse("1991-10-30")));
            employees.add(new Airhostess(null, "Kubeko Diana","37522373457441",formatter.parse("2008-12-01"), null,formatter.parse("1982-06-10")));
            employees.add(new Airhostess(null, "Golovko Olha","37522373457441",formatter.parse("2005-06-21"), null,formatter.parse("1988-05-11")));
        } catch (ParseException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
        saveCollection(session, employees);
    }

    private void generateCommandsPermissions() throws DaoException {
        Session session =  HibernateUtil.getHibernateUtil().getSessionFromThreadLocal();
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
        saveCollection(session, commands);
    }

    private <T> void  saveCollection(Session session, List<T> list) throws DaoException {
        Transaction transaction = null;
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

}
