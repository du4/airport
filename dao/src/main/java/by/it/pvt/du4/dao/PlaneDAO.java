package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Plane;
import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PlaneDAO extends BaseDao <Plane> {
    private static final Logger LOG = LoggerFactory.getLogger(PlaneDAO.class);
    private static volatile PlaneDAO instance;

    private PlaneDAO()  {
    }

    public static  PlaneDAO getInstance(){
        if (instance == null) {
            synchronized (PlaneDAO.class) {
                if(instance == null){
                    instance = new PlaneDAO();
                }
            }
        }
        return instance;
    }
}
