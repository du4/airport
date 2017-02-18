package by.it.pvt.du4.service;

import by.it.pvt.du4.beans.Airport;
import by.it.pvt.du4.dao.IAirportDao;
import by.it.pvt.du4.service.interfaces.IAirportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AirportService extends BaseService<Airport>  implements IAirportService {
    private static  Logger log = LoggerFactory.getLogger(AirportService.class);

    @Autowired
    IAirportDao airportDao;

}
