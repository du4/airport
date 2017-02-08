package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class BaseDaoTest {


    private static Session session = HibernateUtil.getHibernateUtil().getHibernateSession();
    private UserDAO userDAO = DaoFactory.getInstance().getDao(UserDAO.class);
    private static User testUser;
    private static long countBefore = 0;

    @BeforeClass
    public static void init() {
        testUser = new User("testUser1","testuser1@tut.by","test1user1pass", null,  new Date(1000*(new Date().getTime()/1000)));
    }

    @Test
    public void a_saveOrUpdate() throws Exception {
        Transaction transaction = session.beginTransaction();
        countBefore = (Long) HibernateUtil.getHibernateUtil().getHibernateSession().createQuery("SELECT count(*) from User ").uniqueResult();
        userDAO.saveOrUpdate(testUser);
        transaction.commit();
        session.flush();
        session.clear();
        long newCount = (Long) HibernateUtil.getHibernateUtil().getHibernateSession().createQuery("SELECT count(*) from User ").uniqueResult();
        assertTrue(newCount == (countBefore+1));
    }

    @Test
    public void bget() throws Exception {
        User newUser;
        Transaction transaction = session.beginTransaction();
        newUser = userDAO.get(testUser.getId());
        transaction.commit();
        session.flush();
        session.clear();
        assertEquals(newUser, testUser);
    }

    @Test
    public void bsaveOrUpdate() throws Exception {
        Transaction transaction = session.beginTransaction();
        countBefore = getCount();
        testUser.setLogin("newLogin");
        testUser.setEmail("newEmail@airport.by");
        testUser.setPass("111");
        userDAO.saveOrUpdate(testUser);
        transaction.commit();
        session.flush();
        session.clear();
        long countAfter = getCount();
        assertEquals(countBefore, countAfter);
    }

    @Test
    public void c_load() throws Exception {
        User newUser;
        Transaction transaction = session.beginTransaction();
        newUser = userDAO.load(testUser.getId());
        transaction.commit();
        session.flush();
        session.clear();
        assertEquals(newUser.getId(), testUser.getId());
    }

    @Test
    public void d_getAll() throws Exception {
        Transaction transaction = session.beginTransaction();
        long count = getCount();
        List<User> users = userDAO.getAll();
        transaction.commit();
        session.flush();
        session.clear();
        assertEquals((int)count, users.size());
    }

    @Test
    public void e_delete() throws Exception {
        Transaction transaction = session.beginTransaction();
        userDAO.delete(testUser);
        transaction.commit();
        session.flush();
        session.clear();
        long newCount = getCount();
        assertEquals(newCount, 0L);
    }

    private long getCount() {
        return (Long) HibernateUtil.getHibernateUtil().getHibernateSession().createQuery("SELECT count(*) from User ").uniqueResult();
    }
}