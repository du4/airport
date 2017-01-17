package by.it.pvt.du4.dao;

import by.it.pvt.du4.pool.PoolCreator;
import by.it.pvt.du4.dao.exceptions.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class AbstractDAO{
    private static final Logger LOG = LoggerFactory.getLogger(AbstractDAO.class);

    protected int executeUpdate(String sql) throws DaoException {
        int result = -1;
        LOG.trace("executeUpdate:"+sql);
        try (Connection connection = PoolCreator.getConnectionFromPool();
             Statement statement = connection.createStatement()) {
            result = statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
            if (sql.trim().toUpperCase().startsWith("INSERT")) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) result = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            throw  new DaoException(e);
        }
        return result;
    }

    public int getLastID(String idColName, String tableName){
        String sql = "SELECT " + idColName + " FROM " + tableName + " ORDER BY " + idColName + " DESC LIMIT 1;";

        int result = -1;
        try (Connection connection = PoolCreator.getConnectionFromPool();
             Statement statement = connection.createStatement()) {
            LOG.trace("executeQuery:"+sql);
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            result = resultSet.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return result;
    }

}
