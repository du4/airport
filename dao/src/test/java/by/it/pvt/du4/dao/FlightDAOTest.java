package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.*;
import by.it.pvt.du4.dao.interfaces.IFlightDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = "classpath:daoTestSpring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class FlightDAOTest {

    @Autowired
    private IFlightDao flightDao;

    private Flight createFlightInstance(){
        Role role = new Role("testRole");
        User testUser = new User("testUser1","testuser1@tut.by","test1user1pass", role,  new Date(1000*(new Date().getTime()/1000)));
        Airport airportFrom = new Airport("ERT", "Something");
        Airport airportTo = new Airport("QWE", "Nothing");
        Pilot pilot = new Pilot(null, "duch", "141243123412", new Date(1000*(new Date().getTime()/1000)), null, "III", null);
        Airhostess airhostess = new Airhostess(null, "somebody", "11111111", new Date(1000*(new Date().getTime()/1000)), null, new Date(1000*(new Date().getTime()/1000)), null);
        Plane plane = new Plane("Samaliot");
        Flight testFlight = new Flight(null, "AS123","Company", new Date(1000*(new Date().getTime()/1000)),
                new Date(1000*(new Date().getTime()/1000)), plane, airportTo, airportFrom,
                new HashSet<Employee>(){{add(pilot);add(airhostess);}}, testUser, new Date(1000*(new Date().getTime()/1000)));
        pilot.setFlights(new HashSet<Flight>(){{add(testFlight);}});
        airhostess.setFlights(new HashSet<Flight>(){{add(testFlight);}});
        return testFlight;
    }

    @Test
    public void create() throws Exception {
//        Flight testFlight = createFlightInstance();
//        flightDao.create(testFlight);
//        List<Flight> flights = flightDao.getAll(Flight.class);
//        assertEquals(flights.get(0), testFlight);
    }

    @Test
    public void getByFilter() throws Exception {
//        Flight testFlight = createFlightInstance();
//        flightDao.create(testFlight);
//        long curCount = flightDao.getCount(Flight.class).longValue();
//        List<FlightStr> flights = flightDao.getByFilter(null);
//        FlightStr f = flights.get(0);
//        assertTrue(f.getId().equals(testFlight.getId()) && f.getArrival_time().getTime() == testFlight.getArrival_time().getTime() &&
//        f.getDeparture_time().getTime() ==testFlight.getDeparture_time().getTime() && f.getCompany().equals(testFlight.getCompany()) &&
//        f.getFlightCode().equals(testFlight.getFlightCode()) && f.getPlane().equals(testFlight.getPlane_id().getPlaneName()) &&
//        f.getFrom().equals(testFlight.getFrom_id().getAcronim()) && f.getTo().equals(testFlight.getTo_id().getAcronim()) &&
//        f.getUser().equals(testFlight.getUser_id().getLogin()) && f.getCreated_date().getTime() == testFlight.getCreateDate().getTime());
    }

    @Test
    public void getCount() throws Exception {
        assertTrue(flightDao.getCount(Flight.class).longValue() == 0L);
    }

    @Test
    public void getFlightCrew() throws Exception {

    }



}