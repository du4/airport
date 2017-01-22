package by.it.pvt.du4.dao;


import by.it.pvt.du4.beans.Crew;
import by.it.pvt.du4.pool.PoolCreator;
import by.it.pvt.du4.dao.exceptions.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrewDAO extends AbstractDAO implements IDAO<Crew> {

    private static final Logger LOG = LoggerFactory.getLogger(CrewDAO.class);

    @Override
    public int create(Crew entity) throws DaoException{
        int result = -1;
        try(Connection connection =  PoolCreator.getConnectionFromPool();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO crews(pilot1, pilot2, airHostess1, airHostess2) VALUES(?,?,?,?);")
        ){
            preparedStatement.setInt(1, entity.getPilot1_id());
            preparedStatement.setInt(2, entity.getPilot2_id());
            preparedStatement.setInt(3, entity.getAirhostess1_id());
            preparedStatement.setInt(4, entity.getAirhostess2_id());
            result = preparedStatement.executeUpdate();
            LOG.info("Create crew_id="+entity);
        }catch (SQLException e) {
            LOG.error(""+e);
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean update(Crew entity) throws DaoException{
        int result = -1;
        try(Connection connection =  PoolCreator.getConnectionFromPool();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE `crews` SET `pilot1`=?, `pilot2`=?, `airHostess1`=?, `airHostess1`=? WHERE `crews`.`crew_id` = ?;")){
            preparedStatement.setInt(1, entity.getPilot1_id());
            preparedStatement.setInt(2, entity.getPilot2_id());
            preparedStatement.setInt(3, entity.getAirhostess1_id());
            preparedStatement.setInt(4, entity.getAirhostess2_id());
            preparedStatement.setInt(5,entity.getId());
            result = preparedStatement.executeUpdate();
            LOG.info("Update crew_id="+entity);
        }catch (SQLException e) {
            LOG.error(""+e);
            throw new DaoException(e);
        }
        return (result > 0);
    }

    @Override
    public boolean delete(Crew entity) throws DaoException{
        int result = -1;
        try(Connection connection =  PoolCreator.getConnectionFromPool();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `crews` WHERE  `crews`.`crew_id`=?;")
        ){
            preparedStatement.setInt(1, entity.getId());
            result = preparedStatement.executeUpdate();
            LOG.info("Delete crew_id="+entity);
        }catch (SQLException e) {
            LOG.error(""+e);
            throw new DaoException(e);
        }
        return (result > 0);
    }

    @Override
    public Crew read(int id) throws DaoException {
        List<Crew> crews =  getAll("WHERE crew_id=" + id + " LIMIT 0,1");
        return (crews.size() > 0) ? crews.get(0) : null;
    }

    @Override
    public List<Crew> getAll(String WhereAndOrder) throws DaoException{
        List<Crew> crews = new ArrayList<>();
        try (Connection connection = PoolCreator.getConnectionFromPool();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM crews " + WhereAndOrder + ";")) {
            ResultSet resultSet = statement.executeQuery();
            LOG.info("Get users(count="+crews.size()+")");
            while (resultSet.next()){
                crews.add(new Crew(resultSet.getInt("crew_id"),resultSet.getInt("pilot1"),resultSet.getInt("pilot2"),
                        resultSet.getInt("airHostess1"),resultSet.getInt("airHostess1")));
            }
        } catch (SQLException e) {
            LOG.error(""+e);
            throw new DaoException(e);
        }
        return crews;
    }
}
