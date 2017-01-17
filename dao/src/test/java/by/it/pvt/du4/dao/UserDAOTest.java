package by.it.pvt.du4.dao;


import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.beans.User;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class UserDAOTest {

    private UserDAO userDAO = new UserDAO();
    private static User testUser;

    @BeforeClass
    public static void init() {
        testUser = new User("testUser1","testuser1@tut.by","test1user1pass",Role.USER_ROLE);
    }

    @Test
    public void a_create() throws Exception {
        assertTrue(userDAO.create(testUser) > 0);
        testUser.setId(userDAO.getLastID("user_id", "users"));
    }

    @Test
    public void b_update() throws Exception {
        testUser.setPass("test1user1p");
        testUser.setEmail("tu1@tut.com");
        testUser.setRole(Role.DISPATCHER_ROLE);
        assertTrue(userDAO.update(testUser));
    }

    @Test
    public void c_read() throws Exception {
        assertEquals(userDAO.read(testUser.getId()), testUser);
    }

    @Test
    public void delete() throws Exception {
        assertTrue(userDAO.delete(testUser));
    }

}