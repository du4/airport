package by.it.pvt.du4.service.main;

import by.it.pvt.du4.dao.exceptions.DaoException;
import by.it.pvt.du4.service.util.ServiceDataGeneratorUtil;
import by.it.pvt.du4.service.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

public class ServiceMain {
    private static Logger log = LoggerFactory.getLogger(ServiceMain.class);

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        ApplicationContext context = new ClassPathXmlApplicationContext("serviceSpring.xml");

        try {
            ServiceDataGeneratorUtil.getInstance(context).generateData();
        } catch (DaoException | ServiceException e) {
            e.printStackTrace();
        }

//        IService<User> userService = (IService<User>) context.getBean("baseService");
//        IService<Role> roleService = context.getBean("baseService", IService.class);
//
//        Role role = new Role("user");
//        User user = new User("blabla", "bla@mail.ru","123", role, new Date());
//
//        try {
//            roleService.create(role);
//            userService.create(user);
//
//        } catch (ServiceException e) {
//            e.printStackTrace();
//            log.error(""+e);
//        }


    }
}
