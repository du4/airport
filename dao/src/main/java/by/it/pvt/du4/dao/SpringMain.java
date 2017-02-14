package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Plane;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("springContext.xml");
        Plane plane = context.getBean(Plane.class);
        System.out.println(plane);
        ((ClassPathXmlApplicationContext) context).close();
    }

}
