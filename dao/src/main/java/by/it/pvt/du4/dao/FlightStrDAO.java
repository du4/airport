package by.it.pvt.du4.dao;



import by.it.pvt.du4.beans.FlightStr;
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

public class FlightStrDAO extends AbstractDAO implements IDAO <FlightStr> {
    private static final Logger LOG = LoggerFactory.getLogger(CrewDAO.class);
    @Override
    public FlightStr read(int id) {
        return null;
    }

    @Override
    public int create(FlightStr entity) {
        return 0;
    }

    @Override
    public boolean update(FlightStr entity) {
        return false;
    }

    @Override
    public boolean delete(FlightStr entity) {
        return false;
    }

    @Override
    public List<FlightStr> getAll(String WhereAndOrder) throws DaoException {
        List<FlightStr> flights = new ArrayList<>();
        String sql = "SELECT flights.flight_id,flights.flightCode,flights.company," +
                "flights.departure_time, flights.arrival_time , planes.planeName ," +
                "a.acronim toPort, b.acronim fromPort, crews.crew_id,users.login " +
                "FROM airportdb.flights " +
                "INNER JOIN planes ON flights.plane=planes.plane_id " +
                "INNER JOIN airports a ON flights.toPort=a.airports_id " +
                "INNER JOIN airports b ON flights.fromPort=b.airports_id " +
                "INNER JOIN crews ON flights.crew=crews.crew_id " +
                "INNER JOIN users ON flights.user=users.user_id " + WhereAndOrder + ";";
        try (Connection connection = PoolCreator.getConnectionFromPool();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            LOG.trace("executeQuery("+sql+")");
            while (resultSet.next()){
                flights.add(new FlightStr(resultSet.getInt("flight_id"),resultSet.getString("flightCode"),
                        resultSet.getString("company"),resultSet.getTimestamp("departure_time"),resultSet.getTimestamp("arrival_time"),
                        resultSet.getString("planeName"),resultSet.getString(7),resultSet.getString(8),resultSet.getString("crew_id"),
                        resultSet.getString("login")));
            }

        } catch (SQLException e) {
            LOG.error("ERROR"+e.getMessage());
            e.printStackTrace();
            throw new DaoException(e);
        }
        return flights;
    }
    public int getCount(String WHERE){
        int res=0;
        String sql = "SELECT Count(*) FROM flights " + WHERE + " ;";
        try (Connection connection = PoolCreator.getConnectionFromPool();
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
