package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.dao.exceptions.DaoException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

import static org.junit.Assert.*;

public class UserDAOTest {

    private static ApplicationContext context;
    private static UserDAO userDAO;
    private static Session session ;
    private static User testUser;

    @BeforeClass
    public static void init() throws DaoException {
        context = new ClassPathXmlApplicationContext("daoTestSpring.xml");
        userDAO = (UserDAO) context.getBean("userDao");
        session = userDAO.getSession();

        testUser = new User("testUser1","testuser1@tut.by","test1user1pass", null,  new Date(1000*(new Date().getTime()/1000)));
        session.saveOrUpdate(testUser);
//        session.flush();
//        session.clear();
    }

    @Test
    public void getByLoginAndPassword() throws Exception {
        User newUser = userDAO.getByLoginAndPassword(new User("testUser1",null, "test1user1pass", null, null));
//        session.flush();
//        session.clear();
        assertEquals(newUser, testUser);
    }

    @AfterClass
    public static void after() throws DaoException {
        userDAO.delete(testUser);
//        session.flush();
//        session.clear();
    }

}