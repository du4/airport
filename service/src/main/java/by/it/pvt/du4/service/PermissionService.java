package by.it.pvt.du4.service;

import by.it.pvt.du4.beans.Permission;
import by.it.pvt.du4.dao.interfaces.IPermissionDao;
import by.it.pvt.du4.service.interfaces.IPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PermissionService extends BaseService<Permission>  implements IPermissionService {
    private static  Logger log = LoggerFactory.getLogger(PermissionService.class);

    @Autowired
    IPermissionDao permissionDao;

}
