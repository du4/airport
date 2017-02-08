package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Airport;
import by.it.pvt.du4.beans.Flight;
import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Date;

import static org.junit.Assert.*;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class FlightDAOTest {
    private static Session session = HibernateUtil.getHibernateUtil().getHibernateSession();
    private FlightDAO flightDAO = DaoFactory.getInstance().getDao(FlightDAO.class);

    private static Flight testFlight;
    private static Role role;
    private static User testUser;
    private static Airport airportFrom;
    private static Airport airportTo;

    @BeforeClass
    public static void init() {
        role = new  Role("testRole");
        testUser = new User("testUser1","testuser1@tut.by","test1user1pass", null,  new Date(1000*(new Date().getTime()/1000)));
        airportFrom = new Airport("ERT", "Something");
        airportTo = new Airport("QWE", "Nothing");
    }

    @Test
    public void getByFilter() throws Exception {

    }

    @Test
    public void getCount() throws Exception {
        long curCount = (Long) HibernateUtil.getHibernateUtil().getHibernateSession().createQuery("SELECT count(*) from User ").uniqueResult();
        long count = flightDAO.getCount();
        assertEquals(curCount,  count);
    }

    @Test
    public void getFlightCrew() throws Exception {

    }



}