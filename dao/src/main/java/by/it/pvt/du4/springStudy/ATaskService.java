package by.it.pvt.du4.springStudy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("ataskService")
public class ATaskService {
    private static Logger log = LoggerFactory.getLogger(ATaskService.class);

    public void performJob(){
        log.info("Perform Job");
    }

    public void performExceptionJob() throws Exception{
        log.info("Throw exception in Job");
        throw new Exception("ExceptionJob");
    }

    public String preformJobAround(){
        log.info("Perform around Job");
        return "SUCCESS";
    }


    public void performJob(String name) {
        log.info("Job name: "+name);
    }
}
