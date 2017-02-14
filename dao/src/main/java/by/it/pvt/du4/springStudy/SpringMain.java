package by.it.pvt.du4.springStudy;

import by.it.pvt.du4.beans.Plane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
    private static Logger log = LoggerFactory.getLogger(SpringMain.class);
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("springContext.xml");
//        Plane plane = context.getBean(Plane.class);
//        System.out.println(plane);

//        ATaskService bean = (ATaskService)context.getBean("ataskService");
//        bean.performJob("Spring");
//        bean.performJob();
//        bean.preformJobAround();
//        try {
//            bean.performExceptionJob();
//        } catch (Exception e) {
//            log.error(""+e);
//        }

        TaskService ts = (TaskService)context.getBean("taskService");
        ts.performJob();
        ts.preformJobAround();
        ts.performJob("Spring");
        try {
            ts.performExceptionJob();
        } catch (Exception e) {
            log.error(""+e);
        }



        ((ClassPathXmlApplicationContext) context).close();
    }

}
