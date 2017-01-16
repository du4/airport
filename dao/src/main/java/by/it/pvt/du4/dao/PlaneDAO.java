package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Plane;
import by.it.pvt.du4.connection.DataSourceCreator;
import by.it.pvt.du4.dao.exceptions.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlaneDAO extends AbstractDAO implements IDAO<Plane> {
    private static final Logger LOG = LoggerFactory.getLogger(CrewDAO.class);

    @Override
    public Plane read(int id) throws DaoException {
        List<Plane> planes =  getAll("WHERE plane_id=" + id + " LIMIT 0,1");
        return (planes.size() > 0) ? planes.get(0) : null;
    }

    @Override
    public int create(Plane entity) throws DaoException{
        String sql = String.format("INSERT INTO planes(planeName) VALUES('%s');",
                entity.getPlaneName());
        entity.setId(executeUpdate(sql));
        return executeUpdate(sql);
    }

    @Override
    public boolean update(Plane entity) throws DaoException{
        String sql = String.format("UPDATE `planes` SET `planeName`='%s' WHERE `planes`.`plane_id` = %d;",
                entity.getPlaneName(), entity.getId());
        return (0<executeUpdate(sql));
    }

    @Override
    public boolean delete(Plane entity) throws DaoException{
        String sql = String.format("DELETE FROM `planes` WHERE  `planes`.`plane_id`=%d;", entity.getId());
        executeUpdate(sql);
        return (0<executeUpdate(sql));
    }

    @Override
    public List<Plane> getAll(String WhereAndOrder) throws DaoException{
        List<Plane> users = new ArrayList<>();
        String sql = "SELECT * FROM planes " + WhereAndOrder + ";";
        try (Connection connection = DataSourceCreator.getDataSource();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            LOG.trace("executeQuery("+sql+")");
            while (resultSet.next()){
                users.add(new Plane(resultSet.getInt("plane_id"),resultSet.getString("planeName")));
            }
        } catch (SQLException e) {
            LOG.error("ERROR"+e.getMessage());
            e.printStackTrace();
            throw new DaoException(e);
        }
        return users;
    }
}
