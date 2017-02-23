package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.dao.interfaces.IUserDao;
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
public class UserDaoTest {

    @Autowired
    public IUserDao userDao;

    @Test
    public void create() throws Exception {
        Role role = new Role("TestRole");
        User testUser = new User("testUser","testuser@tut.by","pass", role,  new Date(1000*(new Date().getTime()/1000)));
        userDao.create(testUser);
        assertNotNull(testUser.getId());
        User user = (User) userDao.get(User.class, testUser.getId());
        assertEquals(testUser, user);
    }

    @Test
    public void getByLoginAndPassword() throws Exception {
        User testUser = new User("testUser1","testuser1@tut.by","test1user1pass", null,  new Date(1000*(new Date().getTime()/1000)));
        userDao.create(testUser);
        User newUser = userDao.getByLoginAndPassword(new User("testUser1",null, "test1user1pass", null, null));
        assertEquals(newUser, testUser);
    }

    @Test
    public void update() throws Exception {
        User testUser = new User("testUser","testuser1@tut.by","test1user1pass", null,  new Date(1000*(new Date().getTime()/1000)));
        userDao.create(testUser);
        testUser.setPass("123");
        testUser.setLogin("newLogin");
        testUser.setEmail("email@email.com");
        userDao.update(testUser);
        User user = (User) userDao.get(User.class, testUser.getId());
        assertEquals(testUser, user);
    }

    @Test
    public void get() throws Exception {
        User testUser = new User("testUser1","testuser1@tut.by","test1user1pass", new Role("testRole"),  new Date(1000*(new Date().getTime()/1000)));
        userDao.create(testUser);
        User user = (User) userDao.get(User.class, testUser.getId());
        assertEquals(testUser, user);
    }

    @Test
    public void getCount() throws Exception {
        assertTrue(userDao.getCount(User.class).longValue() == 0L);
    }

    @Test
    public void getAll() throws Exception {
        Long countBefore = userDao.getCount(User.class);
        List<User> users = userDao.getAll(User.class);
        assertTrue(countBefore == users.size());

    }

    @Test(expected = DaoException.class)
    public void exceptionTest() throws DaoException {
        userDao.get(User.class, 3);
    }

}