package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.dao.interfaces.IRoleDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;


@ContextConfiguration(locations = "classpath:daoTestSpring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class RoleDAOTest {

    @Autowired
    public IRoleDao roleDao;

    @Test
    public void create() throws Exception {
        Role role = new Role("TestRole");
        roleDao.create(role);
        assertNotNull(role.getId());
        Role role1 = roleDao.get(Role.class, role.getId());
        assertEquals(role1, role);
    }

    @Test
    public void getByName() throws Exception {
        Role role = new Role("TestRole");
        roleDao.create(role);
        Role newRole = roleDao.getByName("TestRole");
        assertEquals(role, newRole);
    }

    @Test
    public void update() throws Exception {
        Role role = new Role("TestRole");
        roleDao.create(role);
        role.setName("newName");
        roleDao.update(role);
        Role newRole = roleDao.get(Role.class, role.getId());
        assertEquals(role, newRole);
    }

    @Test
    public void get() throws Exception {
        Role role = new Role("TestRole");
        roleDao.create(role);
        Role newRole = roleDao.get(Role.class, role.getId());
        assertEquals(role, newRole);
    }

    @Test
    public void getCount() throws Exception {
        assertTrue(roleDao.getCount(Role.class).longValue() == 0L);
    }

    @Test
    public void getAll() throws Exception {
        Long countBefore = roleDao.getCount(Role.class);
        List<Role> roles = roleDao.getAll(Role.class);
        assertTrue(countBefore == roles.size());

    }
}