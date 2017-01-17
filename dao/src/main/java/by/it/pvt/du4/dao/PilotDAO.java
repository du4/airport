package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Pilot;
import by.it.pvt.du4.pool.PoolCreator;
import by.it.pvt.du4.dao.exceptions.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PilotDAO extends AbstractDAO implements IDAO<Pilot> {
    private static final Logger LOG = LoggerFactory.getLogger(CrewDAO.class);
    @Override
    public Pilot read(int id) throws DaoException {
        List<Pilot> pilots =  getAll("WHERE pilot_id=" + id + " LIMIT 0,1");
        return (pilots.size() > 0) ? pilots.get(0) : null;
    }

    @Override
    public int create(Pilot entity) throws DaoException {
        String sql = String.format("INSERT INTO pilots(name, birthDay, phone) VALUES('%s','%s','%s');",
                entity.getName(), entity.getPhoneNumber(), entity.getBirthDay());
//        entity.setId(executeUpdate(sql));
        return executeUpdate(sql);
    }

    @Override
    public boolean update(Pilot entity) throws DaoException{
        String sql = String.format("UPDATE `pilots` SET `name`='%s', `birthDay`='%s', `phone`='%s' WHERE `pilots`.`pilot_id` = %d;",
                entity.getName(), entity.getBirthDay(), entity.getPhoneNumber(),  entity.getId());
        return (0<executeUpdate(sql));
    }

    @Override
    public boolean delete(Pilot entity) throws DaoException{
        String sql = String.format("DELETE FROM `pilots` WHERE  `pilots`.`pilot_id`=%d;", entity.getId());
        executeUpdate(sql);
        return (0<executeUpdate(sql));
    }

    @Override
    public List<Pilot> getAll(String WhereAndOrder) throws DaoException{
        List<Pilot> pilots = new ArrayList<>();
        String sql = "SELECT * FROM pilots " + WhereAndOrder + ";";
        try (Connection connection = PoolCreator.getConnectionFromPool();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            LOG.trace("executeQuery("+sql+")");
            while (resultSet.next()){
                pilots.add(new Pilot(resultSet.getInt("pilot_id"),resultSet.getString("name"),
                        resultSet.getString("phone"), resultSet.getDate("birthDay")));
            }

        } catch (SQLException e) {
            LOG.error("ERROR"+e.getMessage());
            e.printStackTrace();
            throw new DaoException(e);
        }
        return pilots;
    }
}
