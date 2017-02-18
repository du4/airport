package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.dao.interfaces.IDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "classpath:daoTestSpring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class BaseDaoTest {

    @Autowired
    private IDao<User> baseDao;


    @Test
    public void create() throws Exception {
        Role role = new Role("TestRole");
        User testUser = new User("testUser1","testuser1@tut.by","test1user1pass", role,  new Date(1000*(new Date().getTime()/1000)));
        baseDao.create(testUser);
        assertNotNull(testUser.getId());
        User user = (User) baseDao.get(User.class, testUser.getId());
        assertEquals(testUser, user);
    }

    @Test
    public void update() throws Exception {
        User testUser = new User("testUser1","testuser1@tut.by","test1user1pass", null,  new Date(1000*(new Date().getTime()/1000)));
        baseDao.create(testUser);
        testUser.setPass("123");
        testUser.setLogin("newLogin");
        testUser.setEmail("email@email.com");
        baseDao.update(testUser);
        User user = (User) baseDao.get(User.class, testUser.getId());
        assertEquals(testUser, user);
    }

    @Test
    public void get() throws Exception {
        User testUser = new User("testUser1","testuser1@tut.by","test1user1pass", new Role("testRole"),  new Date(1000*(new Date().getTime()/1000)));
        baseDao.create(testUser);
        User user = (User) baseDao.get(User.class, testUser.getId());
        assertEquals(testUser, user);
    }

    @Test
    public void getCount() throws Exception {
        assertTrue(baseDao.getCount(User.class).longValue() == 0L);
    }

    @Test
    public void getAll() throws Exception {
        Long countBefore = baseDao.getCount(User.class);
        List<User> users = baseDao.getAll(User.class);
        assertTrue(countBefore == users.size());

    }

    @Test(expected = DaoException.class)
    public void exceptionTest() throws DaoException {
        baseDao.get(User.class, 3);
    }
}