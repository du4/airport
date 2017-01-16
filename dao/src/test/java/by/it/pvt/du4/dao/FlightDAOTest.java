package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Flight;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class FlightDAOTest {
    FlightDAO flightDAO = new FlightDAO();
    private static Flight testFlight;

    @BeforeClass
    public static void  init() {
        testFlight = new Flight(0,"FF666","Company", new Timestamp(1488009000),new Timestamp(1476271000), 1,1,3,6,1);
    }

    @Test
    public void a_create() throws Exception {
        assertTrue(flightDAO.create(testFlight)>0);
        testFlight.setId(flightDAO.getLastID("flight_id", "flights"));
    }

    @Test
    public void b_update() throws Exception {
        testFlight.setFlightCode("AS123");
        testFlight.setCompany("Lufthanse");
        assertTrue(flightDAO.update(testFlight));
    }

    @Test
    public void c_read() throws Exception {
        assertEquals(flightDAO.read(testFlight.getId()), testFlight);
    }

    @Test
    public void delete() throws Exception {
        assertTrue(flightDAO.delete(testFlight));
    }


}