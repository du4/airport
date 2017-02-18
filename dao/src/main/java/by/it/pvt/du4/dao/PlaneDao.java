package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Plane;
import by.it.pvt.du4.dao.interfaces.IPlaneDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlaneDao extends BaseDao <Plane>  implements IPlaneDao {

    @Autowired
    public PlaneDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
