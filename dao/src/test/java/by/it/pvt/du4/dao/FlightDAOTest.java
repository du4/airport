package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.*;
import by.it.pvt.du4.dao.exceptions.DaoException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class FlightDAOTest {
//    private static Session session = HibernateUtil.getHibernateUtil().getHibernateSession();
//    private FlightDAO flightDAO = DaoFactory.getInstance().getDao(FlightDAO.class);
//
//    private static Flight testFlight;
//    private static Role role;
//    private static User testUser;
//    private static Airport airportFrom;
//    private static Airport airportTo;
//    private static Plane plane;
//    private static Pilot pilot;
//    private static Airhostess airhostess;
//
//    @BeforeClass
//    public static void init() {
//        role = new  Role("testRole");
//        testUser = new User("testUser1","testuser1@tut.by","test1user1pass", role,  new Date(1000*(new Date().getTime()/1000)));
//        airportFrom = new Airport("ERT", "Something");
//        airportTo = new Airport("QWE", "Nothing");
//        pilot = new Pilot(null, "duch", "141243123412", new Date(1000*(new Date().getTime()/1000)), null, "III", null);
//        airhostess = new Airhostess(null, "somebody", "11111111", new Date(1000*(new Date().getTime()/1000)), null, new Date(1000*(new Date().getTime()/1000)), null);
//        plane = new Plane("Samaliot");
//        testFlight = new Flight(null, "AS123","Company", new Date(1000*(new Date().getTime()/1000)),
//                new Date(1000*(new Date().getTime()/1000)), plane, airportTo, airportFrom,
//                new HashSet<Employee>(){{add(pilot);add(airhostess);}}, testUser, new Date(1000*(new Date().getTime()/1000)));
//        pilot.setFlights(new HashSet<Flight>(){{add(testFlight);}});
//        airhostess.setFlights(new HashSet<Flight>(){{add(testFlight);}});
//        Transaction t = session.beginTransaction();
//        session.save(role);
//        session.save(testUser);
//        session.save(airportFrom);
//        session.save(airportTo);
//        session.save(testFlight);
//        t.commit();
//        session.flush();
//        session.clear();
//    }
//
//    @Test
//    public void getByFilter() throws Exception {
//        Transaction t = session.beginTransaction();
//        long curCount = (Long) HibernateUtil.getHibernateUtil().getHibernateSession().createQuery("SELECT count(*) from Flight ").uniqueResult();
//        List<FlightStr> flights = flightDAO.getByFilter(null);
//        FlightStr f = flights.get(0);
//        t.commit();
//        session.flush();
//        session.clear();
//        assertTrue(f.getId().equals(testFlight.getId()) && f.getArrival_time().getTime() == testFlight.getArrival_time().getTime() &&
//        f.getDeparture_time().getTime() ==testFlight.getDeparture_time().getTime() && f.getCompany().equals(testFlight.getCompany()) &&
//        f.getFlightCode().equals(testFlight.getFlightCode()) && f.getPlane().equals(testFlight.getPlane_id().getPlaneName()) &&
//        f.getFrom().equals(testFlight.getFrom_id().getAcronim()) && f.getTo().equals(testFlight.getTo_id().getAcronim()) &&
//        f.getUser().equals(testFlight.getUser_id().getLogin()) && f.getCreated_date().getTime() == testFlight.getCreateDate().getTime() &&
//        curCount == flights.size());
//    }
//
//    @Test
//    public void getCount() throws Exception {
//        long curCount = (Long) HibernateUtil.getHibernateUtil().getHibernateSession().createQuery("SELECT count(*) from Flight ").uniqueResult();
//        long count = flightDAO.getCount();
//        assertEquals(curCount,  count);
//    }
//
//    @Test(expected = DaoException.class)
//    public void empty() throws DaoException {
//        flightDAO.get(3);
//    }
//
//    @Test
//    public void getFlightCrew() throws Exception {
//
//    }
//
//    @AfterClass
//    public static void after() throws DaoException {
//        Transaction t = session.beginTransaction();
//        session.delete(testFlight);
//        session.delete(role);
//        t.commit();
//        session.flush();
//        session.clear();
//    }



}