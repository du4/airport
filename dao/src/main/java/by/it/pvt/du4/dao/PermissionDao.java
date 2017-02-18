package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Permission;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionDao extends BaseDao <Permission> implements IPermissionDao{

    @Autowired
    public PermissionDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
