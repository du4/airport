package by.it.pvt.du4.dao;


import by.it.pvt.du4.beans.Command;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommandDAO extends BaseDao <Command> implements ICommandDao{

    @Autowired
    public CommandDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
