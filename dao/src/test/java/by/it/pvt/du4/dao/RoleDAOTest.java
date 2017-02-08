package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class RoleDAOTest {

    private static Session session = HibernateUtil.getHibernateUtil().getHibernateSession();
    private static Role testRole;
    private static RoleDAO roleDao = DaoFactory.getInstance().getDao(RoleDAO.class);

    @BeforeClass
    public static void init() throws DaoException {
        testRole = new Role("testRole");
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(testRole);
        transaction.commit();
        session.flush();
        session.clear();
    }

    @Test
    public void getByName() throws Exception {
        Transaction transaction = session.beginTransaction();
        Role newRole = roleDao.getByName("testRole");
        transaction.commit();
        session.flush();
        session.clear();
        assertEquals(newRole, testRole);
    }

    @AfterClass
    public static void after() throws DaoException {
        Transaction transaction = session.beginTransaction();
        roleDao.delete(testRole);
        transaction.commit();
        session.flush();
        session.clear();
    }

}