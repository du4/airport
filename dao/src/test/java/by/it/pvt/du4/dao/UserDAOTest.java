package by.it.pvt.du4.dao;

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

public class UserDAOTest {
    private static Session session = HibernateUtil.getHibernateUtil().getHibernateSession();
    private static User testUser;
    private static UserDAO userDAO = DaoFactory.getInstance().getDao(UserDAO.class);

    @BeforeClass
    public static void init() throws DaoException {
        testUser = new User("testUser1","testuser1@tut.by","test1user1pass", null,  new Date(1000*(new Date().getTime()/1000)));
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(testUser);
        transaction.commit();
        session.flush();
        session.clear();
    }

    @Test
    public void getByLoginAndPassword() throws Exception {
        Transaction transaction = session.beginTransaction();
        User newUser = userDAO.getByLoginAndPassword(new User("testUser1",null, "test1user1pass", null, null));
        transaction.commit();
        session.flush();
        session.clear();
        assertEquals(newUser, testUser);
    }

    @AfterClass
    public static void after() throws DaoException {
        Transaction transaction = session.beginTransaction();
        userDAO.delete(testUser);
        transaction.commit();
        session.flush();
        session.clear();
    }

}