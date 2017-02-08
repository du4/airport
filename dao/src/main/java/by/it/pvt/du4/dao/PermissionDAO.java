package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Permission;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PermissionDAO extends BaseDao <Permission>{
    private static final Logger LOG = LoggerFactory.getLogger(PermissionDAO.class);

    public PermissionDAO()  {
    }

}
