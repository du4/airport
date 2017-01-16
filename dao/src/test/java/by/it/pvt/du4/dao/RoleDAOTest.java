package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Role;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class RoleDAOTest {
    private RoleDAO roleDAO = DAO.getDAO().roleDAO;
    private static Role testRole;

    @BeforeClass
    public static void init(){
        testRole = new Role(0, "testRole");
    }

    @Test
    public void a_create() throws Exception {
        assertTrue(roleDAO.create(testRole) > 0);
        testRole.setId(roleDAO.getLastID("role_id","roles"));
    }

    @Test
    public void b_update() throws Exception {
        testRole.setRole("newTestRole");
        assertTrue(roleDAO.update(testRole));
    }

    @Test
    public void c_read() throws Exception {
        assertEquals(testRole, roleDAO.read(testRole.getId()));
    }

    @Test
    public void delete() throws Exception {
        assertTrue(roleDAO.delete(testRole));
    }

}