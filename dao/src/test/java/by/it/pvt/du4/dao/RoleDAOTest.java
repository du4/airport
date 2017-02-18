package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.dao.exceptions.DaoException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class RoleDAOTest {

//    private static Session session = HibernateUtil.getHibernateUtil().getHibernateSession();
//    private static Role testRole, testRole1, testRole2;
//    private static RoleDAO roleDao = DaoFactory.getInstance().getDao(RoleDAO.class);
//
//    @BeforeClass
//    public static void init() throws DaoException {
//        testRole = new Role("testRole");
//        testRole1 = new Role("testRole1");
//        testRole2 = new Role("testRole2");
//        Transaction transaction = session.beginTransaction();
//        session.saveOrUpdate(testRole);
//        session.saveOrUpdate(testRole1);
//        session.saveOrUpdate(testRole2);
//        transaction.commit();
//        session.flush();
//        session.clear();
//    }
//
//    @Test
//    public void a_getByName() throws Exception {
//        Transaction transaction = session.beginTransaction();
//        Role newRole = roleDao.getByName("testRole");
//        transaction.commit();
//        session.flush();
//        session.clear();
//        assertEquals(newRole, testRole);
//    }
//
//    @Test
//    public void b_getAll() throws DaoException{
//        Transaction t = session.beginTransaction();
//        List<Role>roles = roleDao.getAll();
//        t.commit();
//        session.flush();
//        session.clear();
//        assertTrue(roles.get(0).equals(testRole) && roles.get(1).equals(testRole1) && roles.get(2).equals(testRole2));
//    }
//
//    @AfterClass
//    public static void after() throws DaoException {
//        Transaction transaction = session.beginTransaction();
//        roleDao.delete(testRole);
//        roleDao.delete(testRole1);
//        roleDao.delete(testRole2);
//        transaction.commit();
//        session.flush();
//        session.clear();
//    }
//

}