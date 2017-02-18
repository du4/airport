package by.it.pvt.du4.service;

import by.it.pvt.du4.beans.Command;
import by.it.pvt.du4.dao.interfaces.ICommandDao;
import by.it.pvt.du4.service.interfaces.ICommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CommandService extends BaseService<Command>  implements ICommandService {
    private static  Logger log = LoggerFactory.getLogger(CommandService.class);

    @Autowired
    ICommandDao commandDao;

}
