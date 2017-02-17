package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class BaseDaoTest {

    private static ApplicationContext context;
    private static BaseDao<User>baseDao;
    private static Session session ;
    private static User testUser;
    private static long countBefore = 0;

    @BeforeClass
    public static void init() {
        context = new ClassPathXmlApplicationContext("daoTestSpring.xml");
        baseDao = context.getBean(UserDAO.class);
        session = baseDao.getSession();
        testUser = new User("testUser1","testuser1@tut.by","test1user1pass", null,  new Date(1000*(new Date().getTime()/1000)));
    }

    @Test
    public void a_update() throws Exception {
        Transaction transaction = session.beginTransaction();
        countBefore = (Long) HibernateUtil.getHibernateUtil().getHibernateSession().createQuery("SELECT count(*) from User ").uniqueResult();
        baseDao.update(testUser);
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
        newUser = baseDao.get(User.class, testUser.getId());
        transaction.commit();
        session.flush();
        session.clear();
        assertEquals(newUser, testUser);
    }

    @Test
    public void bcreate() throws Exception {
        Transaction transaction = session.beginTransaction();
        countBefore = getCount();
        testUser.setLogin("newLogin");
        testUser.setEmail("newEmail@airport.by");
        testUser.setPass("111");
        baseDao.create(testUser);
        transaction.commit();
        session.flush();
        session.clear();
        long countAfter = getCount();
        assertEquals(countBefore, countAfter);
    }

    @Test
    public void d_getAll() throws Exception {
        Transaction transaction = session.beginTransaction();
        long count = getCount();
        List<User> users = baseDao.getAll(User.class);
        transaction.commit();
        session.flush();
        session.clear();
        assertEquals((int)count, users.size());
    }

    @Test
    public void e_delete() throws Exception {
        Transaction transaction = session.beginTransaction();
        baseDao.delete(testUser);
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