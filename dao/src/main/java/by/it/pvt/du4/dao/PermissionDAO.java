package by.it.pvt.du4.dao;


import by.it.pvt.du4.beans.Permission;
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

public class PermissionDAO extends AbstractDAO implements IDAO<Permission>{
    private static final Logger LOG = LoggerFactory.getLogger(CrewDAO.class);
    @Override
    public Permission read(int id) {
        return null;
    }

    @Override
    public int create(Permission entity) {
        return 0;
    }

    @Override
    public boolean update(Permission entity) {
        return false;
    }

    @Override
    public boolean delete(Permission entity) {
        return false;
    }

    @Override
    public List<Permission> getAll(String WhereAndOrder) throws DaoException {
        List<Permission> permissions = new ArrayList<>();
        String sql = "SELECT * FROM permission " + WhereAndOrder + ";";
        try (Connection connection = PoolCreator.getConnectionFromPool();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            LOG.trace("executeQuery("+sql+")");
            while (resultSet.next()){
                permissions.add(new Permission(resultSet.getInt("id_permission"),resultSet.getInt("fk_role")
                        ,resultSet.getInt("fk_command"),resultSet.getBoolean("permission")));
            }

        } catch (SQLException  e) {
            LOG.error("ERROR"+e.getMessage());
            e.printStackTrace();
            throw new DaoException(e);
        }
        return permissions;
    }
}
