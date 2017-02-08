package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Pilot;
import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PilotDAO extends BaseDao <Pilot> {
    private static final Logger LOG = LoggerFactory.getLogger(PilotDAO.class);

    public PilotDAO()  {
    }

}
