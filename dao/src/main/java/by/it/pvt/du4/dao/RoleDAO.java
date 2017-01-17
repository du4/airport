package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.pool.PoolCreator;
import by.it.pvt.du4.dao.exceptions.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO extends AbstractDAO implements IDAO<Role> {
    private static final Logger LOG = LoggerFactory.getLogger(CrewDAO.class);

    @Override
    public int create(Role role) throws DaoException{
        int result = -1;
        try(Connection connection =  PoolCreator.getConnectionFromPool();
            PreparedStatement preparedStatement = connection.prepareStatement("insert INTO roles(name) values(?);")
        ){
            preparedStatement.setString(1, role.getRole());
            result = preparedStatement.executeUpdate();
            LOG.info("Create role="+role);
        }catch (SQLException e) {
            LOG.error(""+e);
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean update(Role role) throws DaoException{
        int result = -1;
        try(Connection connection =  PoolCreator.getConnectionFromPool();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `roles` SET `name` = ? WHERE `roles`.`role_id` = ?")
        ){
            preparedStatement.setString(1, role.getRole());
            preparedStatement.setInt(2, role.getId());
            result = preparedStatement.executeUpdate();
            LOG.info("Update role="+role);
        }catch (SQLException e) {
            LOG.error(""+e);
            throw new DaoException(e);
        }
        return (result > 0);
    }

    @Override
    public boolean delete(Role role) throws DaoException{
        int result = -1;
        try(Connection connection =  PoolCreator.getConnectionFromPool();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `roles` WHERE `roles`.`role_id` = ?;")
        ){
            preparedStatement.setInt(1, role.getId());
            result = preparedStatement.executeUpdate();
            LOG.info("Delete role="+role);
        }catch (SQLException e) {
            LOG.error(""+e);
            throw new DaoException(e);
        }
        return (result > 0);
    }

    @Override
    public List<Role> getAll(String WHERE) throws DaoException {
        List<Role> roles = new ArrayList<>();
        try (Connection connection = PoolCreator.getConnectionFromPool();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM roles " + WHERE + " ;")
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            LOG.trace("Get roles(count = "+roles.size()+")");
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("role_id"));
                role.setRole(rs.getString("name"));
                roles.add(role);
            }
        } catch (SQLException  e) {
            LOG.error("ERROR"+e.getMessage());
            e.printStackTrace();
            throw new DaoException(e);
        }
        return roles;
    }

    @Override
    public Role read(int id) throws DaoException{
        List<Role> roles = getAll("WHERE role_id=" + id + " LIMIT 0,1");
        if (roles.size() > 0) {
            return roles.get(0);
        } else
            return null;
    }


}
