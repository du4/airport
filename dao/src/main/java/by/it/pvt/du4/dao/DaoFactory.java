package by.it.pvt.du4.dao;

import java.util.HashMap;
import java.util.Map;

public class DaoFactory {

    private static DaoFactory instance;

    public static DaoFactory getInstance() {
        if (instance == null) {
            synchronized (DaoFactory.class) {
                if(instance == null) {
                    instance = new DaoFactory();
                }
            }
        }
        return instance;
    }

    Map<Class<? extends BaseDao>, BaseDao> daos = new HashMap<Class<? extends BaseDao>, BaseDao>();

    public <T extends BaseDao> T getDao(Class<T> daoClazz) {

        T dao = daoClazz.cast(daos.get(daoClazz));
        if (dao != null) {
            return dao;
        }

        // if not existed, create another new one and return
        // cached it, after that it is accessible

        try {
            dao = daoClazz.newInstance();
            daos.put(daoClazz, dao);
            return dao;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
