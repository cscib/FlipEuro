package mt.com.casinoeuro.flipeuro.data.dao;

import mt.com.casinoeuro.flipeuro.data.model.Role;
import mt.com.casinoeuro.flipeuro.data.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * Unit Test for the User Dao.
 * @author cscib
 * @version 1.0.0
 * @since 24/05/2014 15:45
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-flip-euro-servlet.xml"})
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Test
    public void createUserTest(){
        Role role = new Role();
        role.setRoleName("NORMAL");
        roleDao.save(role);

        User user = new User();
        user.setRoleByRoleId(role);

        userDao.save(user);

        assertNotNull(user.getUserId());

        Iterable users =  userDao.findAll();

        assertTrue(users.iterator().hasNext());
    }
}
