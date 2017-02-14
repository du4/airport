package by.it.pvt.du4.springStudy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service("taskService")
public class TaskService {
    private static Logger log = LoggerFactory.getLogger(TaskService.class);

    public void performJob() {
        log.info("Perform Job: calculate fibonacci(100)="+calculate(100));
    }

    public void performJob(String name) {
        log.info("Perform Job: " + name);
    }

   public void performExceptionJob() throws Exception{
        log.info("Throw exception in Job");
        throw new Exception("ExceptionJob from TaskService");
    }

    public String preformJobAround(){
        log.info("Perform around Job");
        return "SUCCESS";
    }



    private BigInteger calculate(Integer n) {
        if (n < 0) throw new IllegalArgumentException("Unsupported negative value.");
        if (n==1)return BigInteger.ZERO;
        if (n==2)return BigInteger.ONE;

        BigInteger bigIntegerF1 = BigInteger.ZERO;
        BigInteger bigIntegerF2 = BigInteger.ONE;
        BigInteger tmpBigInteger;

        for (int i = 2 ; i <= n ; i++){
            tmpBigInteger = bigIntegerF2;
            bigIntegerF2 = bigIntegerF2.add(bigIntegerF1);
            bigIntegerF1 = tmpBigInteger;
        }

        return bigIntegerF2;
    }
}
