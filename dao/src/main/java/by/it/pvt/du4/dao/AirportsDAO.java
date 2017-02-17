package by.it.pvt.du4.dao;


import by.it.pvt.du4.beans.Airport;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AirportsDAO extends BaseDao <Airport> implements IAirportDao{

    @Autowired
    public AirportsDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
