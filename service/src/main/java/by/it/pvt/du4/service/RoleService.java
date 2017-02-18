package by.it.pvt.du4.service;

import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.dao.IRoleDao;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.service.exceptions.ServiceException;
import by.it.pvt.du4.service.interfaces.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class RoleService extends BaseService<Role>  implements IRoleService {
    private static  Logger log = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    IRoleDao roleDao;

    @Override
    public Role getByName(String name) throws ServiceException {
        Role role = null;
        try {
            role = roleDao.getByName(name);
        } catch (DaoException e) {
            e.printStackTrace();
            log.error(""+e);
        }
        return  role;
    }
}
