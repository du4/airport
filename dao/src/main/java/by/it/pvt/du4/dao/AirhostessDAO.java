package by.it.pvt.du4.dao;


import by.it.pvt.du4.beans.Airhostess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AirhostessDAO extends BaseDao <Airhostess> {
    private static final Logger LOG = LoggerFactory.getLogger(Airhostess.class);
    private static volatile AirhostessDAO instance;

    private AirhostessDAO()  {
    }

    public static  AirhostessDAO getInstance(){
        if (instance == null) {
            synchronized (AirhostessDAO.class) {
                if(instance == null){
                    instance = new AirhostessDAO();
                }
            }
        }
        return instance;
    }
}
