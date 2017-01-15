package by.it.pvt.du4.dao;


import by.it.pvt.du4.beans.Command;
import by.it.pvt.du4.connection.ConnectionCreator;
import by.it.pvt.du4.dao.exceptions.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommandDAO extends AbstractDAO implements IDAO <Command> {
    static final Logger LOG = LoggerFactory.getLogger(CommandDAO.class);

    @Override
    public Command read(int id) throws DaoException {
        List<Command> commands =  getAll("WHERE id_commands=" + id + " LIMIT 0,1");
        return (commands.size() > 0) ? commands.get(0) : null;
    }

    @Override
    public int create(Command entity) throws DaoException{
        String sql = String.format("INSERT INTO commands(name) VALUES('%s');",
                entity.getName());
        return executeUpdate(sql);
    }

    @Override
    public boolean update(Command entity) throws DaoException{
        String sql = String.format("UPDATE `commands` SET `name`='%s' WHERE `commands`.`id_commands` = %d;", entity.getName(), entity.getId());
        return (0<executeUpdate(sql));
    }

    @Override
    public boolean delete(Command entity) throws DaoException{
        String sql = String.format("DELETE FROM `commands` WHERE  `commands`.`id_commands`=%d;", entity.getId());
        executeUpdate(sql);
        return (0<executeUpdate(sql));
    }

    @Override
    public List <Command> getAll(String WhereAndOrder) throws DaoException{

        List<Command> commands = new ArrayList<>();
        String sql = "SELECT * FROM commands " + WhereAndOrder + ";";
        try (Connection connection = ConnectionCreator.getDataSource();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            LOG.trace("executeQuery("+sql+")");
            while (resultSet.next()){
                commands.add(new Command(resultSet.getInt("id_commands"),resultSet.getString("name")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("ERROR"+e.getMessage());
            throw new DaoException(e);
        }
        return commands;
    }
}
