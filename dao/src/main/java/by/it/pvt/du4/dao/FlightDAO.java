package by.it.pvt.du4.dao;


import by.it.pvt.du4.beans.Flight;
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

public class FlightDAO extends AbstractDAO implements IDAO<Flight> {
    private static final Logger LOG = LoggerFactory.getLogger(CrewDAO.class);
    @Override
    public Flight read(int id) throws DaoException {
        List<Flight> flights =  getAll("WHERE flight_id=" + id + " LIMIT 0,1");
        return (flights.size() > 0) ? flights.get(0) : null;
    }

    @Override
    public int create(Flight entity) throws DaoException{
        String sql = String.format("INSERT INTO flights(flightCode, company, departure_time, arrival_time,plane,toPort,fromPort,crew,user) " +
                        "VALUES('%s','%s','%s','%s',%d,%d,%d,%d,%d);",
                entity.getFlightCode(), entity.getCompany(), entity.getDeparture_time().toString(), entity.getArrival_time().toString(),
                entity.getPlane(),entity.getTo(),entity.getFrom(), entity.getCrew(),entity.getUser());
//        entity.setId(executeUpdate(sql));
        return executeUpdate(sql);
    }

    @Override
    public boolean update(Flight entity) throws DaoException{
        String sql = String.format("UPDATE `flights` SET `flightCode`='%s', `company`='%s', `departure_time`='%s', `arrival_time`='%s' " +
                        ",`plane`=%d,`toPort`=%d,`fromPort`=%d,`crew`=%d,`user`=%d" +
                        "WHERE `flights`.`flight_id` = %d;",
                entity.getFlightCode(), entity.getCompany(), entity.getDeparture_time().toString(), entity.getArrival_time().toString(),
                entity.getPlane(),entity.getTo(),entity.getFrom(), entity.getCrew(),entity.getUser(), entity.getId());
        return (0<executeUpdate(sql));
    }

    @Override
    public boolean delete(Flight entity) throws DaoException{
        String sql = String.format("DELETE FROM `flights` WHERE  `flights`.`flight_id`=%d;", entity.getId());
        executeUpdate(sql);
        return (0<executeUpdate(sql));
    }

    @Override
    public List<Flight> getAll(String WhereAndOrder) throws DaoException{
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flights " + WhereAndOrder + ";";
        try (Connection connection = ConnectionCreator.getDataSource();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            LOG.trace("executeQuery("+sql+")");
            while (resultSet.next()){
                flights.add(new Flight(resultSet.getInt("flight_id"),resultSet.getString("flightCode"),
                        resultSet.getString("company"),resultSet.getTimestamp("departure_time"),resultSet.getTimestamp("arrival_time"),
                        resultSet.getInt("plane"),resultSet.getInt("toPort"),resultSet.getInt("fromPort"),resultSet.getInt("crew"),
                        resultSet.getInt("user")));
            }

        } catch (SQLException  e) {
            LOG.error("ERROR"+e.getMessage());
            e.printStackTrace();
            throw new DaoException(e);

        }
        return flights;
    }

    public int getCount(String WHERE){
        int res=0;
        String sql = "SELECT Count(*) FROM flights " + WHERE + " ;";
        try (Connection connection = ConnectionCreator.getDataSource();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)
        ) {
            if (rs.next()) {
                res=(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
