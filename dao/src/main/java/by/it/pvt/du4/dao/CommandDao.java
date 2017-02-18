package by.it.pvt.du4.dao;


import by.it.pvt.du4.beans.Command;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommandDao extends BaseDao <Command> implements ICommandDao{

    @Autowired
    public CommandDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
