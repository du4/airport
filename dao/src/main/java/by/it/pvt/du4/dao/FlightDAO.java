package by.it.pvt.du4.dao;


import by.it.pvt.du4.beans.Flight;
import by.it.pvt.du4.pool.PoolCreator;
import by.it.pvt.du4.dao.exceptions.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO extends AbstractDAO implements IDAO<Flight> {
    private static final Logger LOG = LoggerFactory.getLogger(CrewDAO.class);

    @Override
    public int create(Flight entity) throws DaoException{
        int result = -1;
        try(Connection connection =  PoolCreator.getConnectionFromPool();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO flights(flightCode, company, departure_time, " +
                    "arrival_time,plane,toPort,fromPort,crew,user, createDate) VALUES(?,?,?,?,?,?,?,?,?,?);")){
            preparedStatement.setString(1, entity.getFlightCode());
            preparedStatement.setString(2, entity.getCompany());
            preparedStatement.setTimestamp(3, entity.getDeparture_time());
            preparedStatement.setTimestamp(4, entity.getArrival_time());
            preparedStatement.setInt(5,entity.getPlane_id());
            preparedStatement.setInt(6,entity.getTo_id());
            preparedStatement.setInt(7,entity.getFrom_id());
            preparedStatement.setInt(8,entity.getCrew_id());
            preparedStatement.setInt(9,entity.getUser_id());
            preparedStatement.setTimestamp(10, entity.getCreateDate());
            result = preparedStatement.executeUpdate();
            LOG.info("Create flight="+entity);
        }catch (SQLException e) {
            LOG.error(""+e);
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean update(Flight entity) throws DaoException{
        int result = -1;
        try(Connection connection =  PoolCreator.getConnectionFromPool();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `flights` SET `flightCode`=?, `company`=?," +
                    " `departure_time`=?, `arrival_time`=? " +
                    ",`plane`=?,`toPort`=?,`fromPort`=?,`crew`=?,`user`=? ,`createDate`=? " +
                    " WHERE `flights`.`flight_id` = ?;")){
            preparedStatement.setString(1, entity.getFlightCode());
            preparedStatement.setString(2, entity.getCompany());
            preparedStatement.setTimestamp(3, entity.getDeparture_time());
            preparedStatement.setTimestamp(4, entity.getArrival_time());
            preparedStatement.setInt(5,entity.getPlane_id());
            preparedStatement.setInt(6,entity.getTo_id());
            preparedStatement.setInt(7,entity.getFrom_id());
            preparedStatement.setInt(8,entity.getCrew_id());
            preparedStatement.setInt(9,entity.getUser_id());
            preparedStatement.setTimestamp(10,entity.getCreateDate());
            preparedStatement.setInt(11,entity.getId());
            result = preparedStatement.executeUpdate();
            LOG.info("Update flight="+entity);
        }catch (SQLException e) {
            LOG.error(""+e);
            throw new DaoException(e);
        }
        return (result > 0);
    }

    @Override
    public boolean delete(Flight entity) throws DaoException{
        int result = -1;
        try(Connection connection =  PoolCreator.getConnectionFromPool();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `flights` WHERE  `flights`.`flight_id`=?;")
        ){
            preparedStatement.setInt(1, entity.getId());
            result = preparedStatement.executeUpdate();
            LOG.info("Delete flight="+entity);
        }catch (SQLException e) {
            LOG.error(""+e);
            throw new DaoException(e);
        }
        return (result > 0);
    }

    @Override
    public List<Flight> getAll(String WhereAndOrder) throws DaoException{
        List<Flight> flights = new ArrayList<>();
        try (Connection connection = PoolCreator.getConnectionFromPool();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM flights " + WhereAndOrder + ";")) {
            ResultSet resultSet = statement.executeQuery();
            LOG.info("Get flights(count="+flights.size()+")");
            while (resultSet.next()){
                flights.add(new Flight(resultSet.getInt("flight_id"),resultSet.getString("flightCode"),
                        resultSet.getString("company"),resultSet.getTimestamp("departure_time"),resultSet.getTimestamp("arrival_time"),
                        resultSet.getInt("plane"),resultSet.getInt("toPort"),resultSet.getInt("fromPort"),resultSet.getInt("crew"),
                        resultSet.getInt("user"), resultSet.getTimestamp("createDate")));
            }
        } catch (SQLException  e) {
            LOG.error(""+e);
            throw new DaoException(e);
        }
        return flights;
    }

    @Override
    public Flight read(int id) throws DaoException {

        Flight flight = null;
        try (Connection connection = PoolCreator.getConnectionFromPool();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM flights  WHERE `flights`.`flight_id`=?;")) {
             statement.setInt(1, id);
             ResultSet resultSet = statement.executeQuery();
             LOG.info("Get flight where id ="+id);
            while (resultSet.next()){
                flight = new Flight(resultSet.getInt("flight_id"),resultSet.getString("flightCode"),
                        resultSet.getString("company"),resultSet.getTimestamp("departure_time"),resultSet.getTimestamp("arrival_time"),
                        resultSet.getInt("plane"),resultSet.getInt("toPort"),resultSet.getInt("fromPort"),resultSet.getInt("crew"),
                        resultSet.getInt("user"), resultSet.getTimestamp("createDate"));
            }
        } catch (SQLException  e) {
            LOG.error(""+e);
            throw new DaoException(e);
        }
        return flight;
    }
}
