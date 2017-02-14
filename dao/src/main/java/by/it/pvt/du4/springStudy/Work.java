package by.it.pvt.du4.springStudy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("work")
public class Work {
    private static Logger log = LoggerFactory.getLogger(Work.class);

    public void beforeWork() {
        log.info("Execution before work");
    }

    public void afterWork() {
        log.info("Execution after work");
    }

    public void afterWorkProblems() {
        log.info("Execution after work problems");
    }

    public void aroundWork(ProceedingJoinPoint joinPoint) {
        try {
            log.info("start");
            log.info("Begin Time:" + System.currentTimeMillis());
            String result = (String)joinPoint.proceed();
            log.info("Finish Time:" + System.currentTimeMillis());
            log.info("Status:"+result);
        } catch (Throwable throwable) {
            log.error(""+throwable);
        }
    }

    public void interceptWorkName(String name) {
        log.info("Intercepted work is "+name);
    }

}
