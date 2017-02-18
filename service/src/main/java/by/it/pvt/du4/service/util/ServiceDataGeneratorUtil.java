package by.it.pvt.du4.service.util;

import by.it.pvt.du4.beans.*;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.service.UserService;
import by.it.pvt.du4.service.exceptions.ServiceException;
import by.it.pvt.du4.service.interfaces.IService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class ServiceDataGeneratorUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceDataGeneratorUtil.class);
    private static SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat formatterDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static volatile ServiceDataGeneratorUtil instance;
    private ApplicationContext context;
    private ServiceDataGeneratorUtil(ApplicationContext context) {
        this.context =  context;
    }

    public static ServiceDataGeneratorUtil getInstance(ApplicationContext context){
        if (instance == null) {
            synchronized (ServiceDataGeneratorUtil.class) {
                if(instance == null){
                    instance = new ServiceDataGeneratorUtil(context);
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
        generateFlights();
        generateCrews();
        generatePermissions();
    }

    private void generateCrews() throws ServiceException {
        List<Pilot> pilots = context.getBean("baseService", IService.class).getAll(Pilot.class);
        List<Airhostess> airhostess = context.getBean("baseService", IService.class).getAll(Airhostess.class);
        List<Flight> flights = context.getBean("baseService", IService.class).getAll(Flight.class);

        Flight flight = flights.get(0);
        flight.setEmployees(new HashSet<>());
        flight.getEmployees().add(pilots.get(0));
        flight.getEmployees().add(pilots.get(1));
        flight.getEmployees().add(airhostess.get(0));
        flight.getEmployees().add(airhostess.get(1));

        pilots.get(0).setFlights(new HashSet<>());
        pilots.get(0).getFlights().add(flight);
        pilots.get(1).setFlights(new HashSet<>());
        pilots.get(1).getFlights().add(flight);
        airhostess.get(0).setFlights(new HashSet<>());
        airhostess.get(0).getFlights().add(flight);
        airhostess.get(1).setFlights(new HashSet<>());
        airhostess.get(1).getFlights().add(flight);

        flight = flights.get(1);
        flight.setEmployees(new HashSet<>());
        flight.getEmployees().add(pilots.get(2));
        flight.getEmployees().add(pilots.get(3));
        flight.getEmployees().add(airhostess.get(2));
        flight.getEmployees().add(airhostess.get(3));
        pilots.get(2).setFlights(new HashSet<>());
        pilots.get(2).getFlights().add(flight);
        pilots.get(3).setFlights(new HashSet<>());
        pilots.get(3).getFlights().add(flight);
        airhostess.get(2).setFlights(new HashSet<>());
        airhostess.get(2).getFlights().add(flight);
        airhostess.get(3).setFlights(new HashSet<>());
        airhostess.get(3).getFlights().add(flight);

        saveCollection(flights);
    }

    private void generatePermissions() throws ServiceException {
        List<Command>commands = context.getBean("baseService", IService.class).getAll(Command.class);
        List<Role>roles = context.getBean("baseService", IService.class).getAll(Role.class);

        List<Permission>permissions = new ArrayList<>();
        //error
        permissions.add(new Permission(null, roles.get(0),commands.get(0),true));
        permissions.add(new Permission(null, roles.get(1),commands.get(0),true));
        permissions.add(new Permission(null, roles.get(2),commands.get(0),true));
        //index
        permissions.add(new Permission(null, roles.get(0),commands.get(1),true));
        permissions.add(new Permission(null, roles.get(1),commands.get(1),true));
        permissions.add(new Permission(null, roles.get(2),commands.get(1),true));
        //login
        permissions.add(new Permission(null, roles.get(0),commands.get(2),true));
        permissions.add(new Permission(null, roles.get(1),commands.get(2),true));
        permissions.add(new Permission(null, roles.get(2),commands.get(2),true));
        //logout
        permissions.add(new Permission(null, roles.get(0),commands.get(3),true));
        permissions.add(new Permission(null, roles.get(1),commands.get(3),true));
        permissions.add(new Permission(null, roles.get(2),commands.get(3),true));
        //newflight
        permissions.add(new Permission(null, roles.get(0),commands.get(4),true));
        permissions.add(new Permission(null, roles.get(1),commands.get(4),false));
        permissions.add(new Permission(null, roles.get(2),commands.get(4),false));
        //profile
        permissions.add(new Permission(null, roles.get(0),commands.get(5),true));
        permissions.add(new Permission(null, roles.get(1),commands.get(5),true));
        permissions.add(new Permission(null, roles.get(2),commands.get(5),true));
        //signup
        permissions.add(new Permission(null, roles.get(0),commands.get(6),true));
        permissions.add(new Permission(null, roles.get(1),commands.get(6),true));
        permissions.add(new Permission(null, roles.get(2),commands.get(6),true));
        //stuffingcrew
        permissions.add(new Permission(null, roles.get(0),commands.get(7),true));
        permissions.add(new Permission(null, roles.get(1),commands.get(7),true));
        permissions.add(new Permission(null, roles.get(2),commands.get(7),false));
        //usermanagement
        permissions.add(new Permission(null, roles.get(0),commands.get(8),true));
        permissions.add(new Permission(null, roles.get(1),commands.get(8),false));
        permissions.add(new Permission(null, roles.get(2),commands.get(8),false));

        saveCollection(permissions);
    }

    private void generateFlights() throws ServiceException {
        List<Flight>flights = new ArrayList<>();
            try {
                List<User>users = context.getBean("baseService", IService.class).getAll(User.class);
                List<Role>roles = context.getBean("baseService", IService.class).getAll(Role.class);

                Flight flight = new Flight(null, "AS123", "Belavia", formatterDateTime.parse("2016-12-04 13:30"), formatterDateTime.parse("2016-12-04 17:00"),
                        new Plane("Kukuruznik") , new Airport("VNO", "Vilnius") , new Airport("KBP", "Borispol"), null, users.get(0), new Date());
                users.get(0).setFlights(new HashSet<>());
                users.get(0).getFlights().add(flight);
                flights.add(flight);
                flight = new Flight(null, "AS222", "Lufthansa", formatterDateTime.parse("2017-02-24 07:30"), formatterDateTime.parse("2017-02-24 11:30"),
                        new Plane("AN-24") , new Airport("TRN", "Turin"), new Airport("FRA", "Frankfurt"), null,
                        new User("qwerty","qwerty@airport.by", DigestUtils.md5Hex(UserService.getSalt() + "qwerty"), roles.get(1), new Date())
                        , new Date());
                flights. add(flight);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        saveCollection(flights);
    }

    private void generateRolesAndUsers() throws ServiceException {
        List<Role>roles = new ArrayList<Role>(){{
                add(new Role("admin"));
                add(new Role("dispatcher"));
                add(new Role("user"));
            }};
        saveCollection(roles);
        List<User>users = new ArrayList<User>(){{
                add(new User(null, "admin", "admin@airport.by", DigestUtils.md5Hex(UserService.getSalt() + "admin"), roles.get(0), new Date(), null, null));
                add(new User(null, "dispatcher", "dispatcher@airport.by", DigestUtils.md5Hex(UserService.getSalt() + "dispatcher"), roles.get(1), new Date(), null, null));
                add(new User(null, "user", "user@airport.by",DigestUtils.md5Hex(UserService.getSalt() + "user") , roles.get(2), new Date(), null, null));
                add(new User(null, "duch", "duch@airport.by",DigestUtils.md5Hex(UserService.getSalt() + "111"),roles.get(0), new Date(), null, null ));
            }};
        saveCollection(users);
    }

    private void generateAirports() throws ServiceException {
        List<Airport>airports = new ArrayList<Airport>(){{
                add(new Airport("MSQ", "Minsk-2"));
                add(new Airport("VIE", "Vienna"));
                add(new Airport("TGD", "Golubovichi"));
                add(new Airport("SVO", "Sheremetyevo"));
                add(new Airport("BKA", "Bukavo"));
            }};
        saveCollection(airports);
    }

    private void generatePlanes() throws ServiceException {
        List<Plane>planes = new ArrayList<Plane>(){{
            add(new Plane("Boeing-747"));
            add(new Plane("Boeing-727"));
            add(new Plane("Boeing 737-500"));
            add(new Plane("Airbus A320"));
            add(new Plane("Gulfstream 650"));
            add(new Plane("Embraer EMB190"));

        }};
        saveCollection(planes);
    }

    private void generateEmployees() throws ServiceException {
        List<Employee>employees = new ArrayList<>();
        try{
            employees.add(new Employee(null, "Aristarh Nikodimych", "11111111", formatterDate.parse("1990-05-14"), formatterDate.parse("2016-04-14"), null));
            employees.add(new Pilot(null,"Dubatovka Sergey","375298784275",  formatterDate.parse("2003-04-14"), null , "II", null));
            employees.add(new Pilot(null,"Averuk Evgeniy","37529673457441",  formatterDate.parse("2013-01-04"), null , "I", null));
            employees.add(new Pilot(null,"Vasiliev Andrew","375291234567",  formatterDate.parse("1999-05-31"), null, "II", null));
            employees.add(new Pilot(null,"Dubovik Pavel","375292224567",  formatterDate.parse("1991-12-01"), null, "I", null));
            employees.add(new Airhostess(null, "Dzyachkova Inha", "37529673457441", formatterDate.parse("2001-02-31"), null, formatterDate.parse("1981-12-31"), null));
            employees.add(new Airhostess(null, "Zhravkova Stefania", "37523273457441", formatterDate.parse("2011-02-01"), null, formatterDate.parse("1991-10-30"), null));
            employees.add(new Airhostess(null, "Kubeko Diana","37522373457441", formatterDate.parse("2008-12-01"), null, formatterDate.parse("1982-06-10"), null));
            employees.add(new Airhostess(null, "Golovko Olha","37522373457441", formatterDate.parse("2005-06-21"), null, formatterDate.parse("1988-05-11"), null));
        } catch (ParseException e) {
            LOG.error(""+e);
            throw new ServiceException(e);
        }
        saveCollection(employees);
    }

    private void generateCommandsPermissions() throws DaoException, ServiceException {
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
        saveCollection(commands);
    }

    private <T> void  saveCollection(List<T> list) throws ServiceException {
        IService<T> service = context.getBean("baseService", IService.class);
            for(T t: list){
                service.create(t);
            }
    }

}
