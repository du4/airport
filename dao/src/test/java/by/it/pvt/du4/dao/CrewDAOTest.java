package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Crew;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class CrewDAOTest {
    private CrewDAO crewDAO = DAO.getDAO().crewDAO;
    private static Crew testCrew;

    @BeforeClass
    public static void init(){
        testCrew = new Crew(0, 1, 1, 1, 1);
    }

    @Test
    public void a_create() throws Exception {
        assertTrue(crewDAO.create(testCrew) > 0);
        testCrew.setId(crewDAO.getLastID("crew_id","crews"));
    }

    @Test
    public void b_update() throws Exception {
        testCrew.setAirhostess1(2);
        testCrew.setAirhostess2(2);
        testCrew.setPilot1(2);
        testCrew.setPilot2(2);
        assertTrue(crewDAO.update(testCrew));
    }

    @Test
    public void c_read() throws Exception {
        assertEquals(testCrew, crewDAO.read(testCrew.getId()));
    }

    @Test
    public void delete() throws Exception {
        assertTrue(crewDAO.delete(testCrew));
    }

}