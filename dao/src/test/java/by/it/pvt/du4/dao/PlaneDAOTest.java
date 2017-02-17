package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Plane;
import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PlaneDAOTest {

//    private static Session session = HibernateUtil.getHibernateUtil().getHibernateSession();
//    private static Plane testPlane, testPlane1, testPlane2;
//    private static PlaneDAO planeDAO = DaoFactory.getInstance().getDao(PlaneDAO.class);
//
//
//    @Before
//    public void setUp() throws Exception {
//        testPlane = new Plane("testRole");
//        testPlane1 = new Plane("testRole1");
//        testPlane2 = new Plane("testRole2");
//        Transaction transaction = session.beginTransaction();
//        session.saveOrUpdate(testPlane);
//        session.saveOrUpdate(testPlane1);
//        session.saveOrUpdate(testPlane2);
//        transaction.commit();
//        session.flush();
//        session.clear();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        Transaction transaction = session.beginTransaction();
//        planeDAO.delete(testPlane);
//        planeDAO.delete(testPlane1);
//        planeDAO.delete(testPlane2);
//        transaction.commit();
//        session.flush();
//        session.clear();
//    }
//
//    @Test
//    public void getAll() throws Exception {
//        Transaction t = session.beginTransaction();
//        List<Plane> planes = planeDAO.getAll();
//        t.commit();
//        session.flush();
//        session.clear();
//        assertTrue(planes.get(0).equals(testPlane) && planes.get(1).equals(testPlane1) && planes.get(2).equals(testPlane2));
//    }

}