package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Flight;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class FlightDAOTest {
    FlightDAO flightDAO = new FlightDAO();
    Flight fs = new Flight(1,"FF666","Company", new Timestamp(1488009000),new Timestamp(1476271800), 1,1,3,6,1);
    Flight fs1 = new Flight(14,"AS123","Lufthanse", new Timestamp(1476293400000L),new Timestamp(1487995200000L), 2,3,2,7,1);

    @Test
    public void C_update() throws Exception {
        assertTrue(flightDAO.update(fs1));
    }

    @Test
    public void B_read() throws Exception {
        assertEquals(flightDAO.read(14), fs1);
    }

    @Test
    public void A_create() throws Exception {
        assertTrue(flightDAO.create(fs)>0);
    }

    @Test
    public void D_delete() throws Exception {
        int id = flightDAO.getLastID("flight_id", "flights");
        fs.setId(id);
        assertTrue(flightDAO.delete(fs));
    }


}