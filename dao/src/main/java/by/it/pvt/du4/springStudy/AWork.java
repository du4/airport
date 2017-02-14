package by.it.pvt.du4.springStudy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AWork {
    private static Logger log = LoggerFactory.getLogger(AWork.class);

    @Pointcut("execution(* by.it.pvt.du4.springStudy.ATaskService.performJob())")
    public void performance(){
    }

    @Pointcut("execution(* by.it.pvt.du4.springStudy.ATaskService.performJob(String)) && args(name)")
    public void intercept(String name){
    }

    @Before("performance()")
    public void beforeWork(){
        log.info("Execution before work");
    }

    @AfterReturning("performance()")
    public void afterWork(){
        log.info("Execution after work");
    }

    @AfterThrowing("performance()")
    public void afterWorkPerformance(){
        log.info("Execution after work problems");
    }

    @Around("performance()")
    public void aroundWork(ProceedingJoinPoint joinPoint) {
        try {
            log.info("Start");
            log.info("Begin time:" + System.currentTimeMillis());
            String result = (String)joinPoint.proceed();
            log.info("Finish time:" + System.currentTimeMillis());
            log.info("Status: "+ result);
        } catch (Throwable throwable) {
            log.error(""+throwable);
        }
    }

    @Before("intercept(name)")
    public void interceptWorkName(String name){
        log.info("Intercepted work is "+ name);
    }
}
