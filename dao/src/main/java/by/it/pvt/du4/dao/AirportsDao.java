package by.it.pvt.du4.dao;


import by.it.pvt.du4.beans.Airport;
import by.it.pvt.du4.dao.interfaces.IAirportDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AirportsDao extends BaseDao <Airport> implements IAirportDao {

    @Autowired
    public AirportsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
