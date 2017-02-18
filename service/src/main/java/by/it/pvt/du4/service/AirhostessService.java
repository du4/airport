package by.it.pvt.du4.service;

import by.it.pvt.du4.beans.Airhostess;
import by.it.pvt.du4.dao.IAirhostessDao;
import by.it.pvt.du4.service.interfaces.IAirhostessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AirhostessService extends BaseService<Airhostess>  implements IAirhostessService {
    private static  Logger log = LoggerFactory.getLogger(AirhostessService.class);

    @Autowired
    IAirhostessDao airhostessDao;

}
