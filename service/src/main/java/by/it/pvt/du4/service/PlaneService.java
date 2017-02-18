package by.it.pvt.du4.service;

import by.it.pvt.du4.beans.Plane;
import by.it.pvt.du4.dao.IPlaneDao;
import by.it.pvt.du4.service.interfaces.IPlaneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PlaneService extends BaseService<Plane>  implements IPlaneService {
    private static  Logger log = LoggerFactory.getLogger(PlaneService.class);

    @Autowired
    IPlaneDao planeDao;

}
