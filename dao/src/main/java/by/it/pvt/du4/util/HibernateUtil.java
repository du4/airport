package by.it.pvt.du4.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {
    private static volatile HibernateUtil util = null;
    private SessionFactory sessionFactory = null;
    private final ThreadLocal threadLocal = new ThreadLocal();
    private static final Logger LOG = LoggerFactory.getLogger(HibernateUtil.class);

    private HibernateUtil() {
        try {
            Configuration configuration = new Configuration().configure().setNamingStrategy(new CustomNamingStrategy());
            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
            serviceRegistryBuilder.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            LOG.error("Initial SessionFactory creation failed." + ex);
            System.exit(0);
        }
    }

    public Session getSessionFromThreadLocal() {
        Session session = (Session) threadLocal.get();
        if (session == null) {
            session = sessionFactory.openSession();
            threadLocal.set(session);
        }
        return session;
    }

    public static synchronized HibernateUtil getHibernateUtil(){
        if (util == null){
            util = new HibernateUtil();
        }
        return util;
    }
}
