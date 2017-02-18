package by.it.pvt.du4.service;

import by.it.pvt.du4.beans.Pilot;
import by.it.pvt.du4.dao.interfaces.IPilotDao;
import by.it.pvt.du4.service.interfaces.IPilotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PilotService extends BaseService<Pilot>  implements IPilotService {
    private static  Logger log = LoggerFactory.getLogger(PilotService.class);

    @Autowired
    IPilotDao pilotDao;

}
