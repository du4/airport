package by.it.pvt.du4.dao;


import by.it.pvt.du4.beans.Command;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CommandDAO extends BaseDao <Command> {
    static final Logger LOG = LoggerFactory.getLogger(CommandDAO.class);

}
