package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Plane;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.dao.interfaces.IPlaneDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class PlaneDAOTest {


    @Autowired
    public IPlaneDao planeDao;

    @Test
    public void create() throws Exception {
        Plane plane = new Plane("TestPlane");
        planeDao.create(plane);
        assertNotNull(plane.getId());
        Plane plane1 = planeDao.get(Plane.class, plane.getId());
        assertEquals(plane1, plane);
    }

    @Test
    public void update() throws Exception {
        Plane plane = new Plane("TestPlane");
        planeDao.create(plane);
        plane.setPlaneName("newName");
        planeDao.update(plane);
        Plane newPlane = planeDao.get(Plane.class, plane.getId());
        assertEquals(plane, newPlane);
    }

    @Test
    public void get() throws Exception {
        Plane plane = new Plane("TestPlane");
        planeDao.create(plane);
        Plane newPlane = planeDao.get(Plane.class, plane.getId());
        assertEquals(plane, newPlane);
    }

    @Test
    public void getCount() throws Exception {
        assertTrue(planeDao.getCount(Plane.class).longValue() == 0L);
    }

    @Test
    public void getAll() throws Exception {
        Long countBefore = planeDao.getCount(Plane.class);
        List<Plane> roles = planeDao.getAll(Plane.class);
        assertTrue(countBefore == roles.size());

    }

}