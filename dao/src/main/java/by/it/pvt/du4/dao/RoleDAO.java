package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Role;
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

public class RoleDAO extends AbstractDAO implements IDAO<Role> {
    private static final Logger LOG = LoggerFactory.getLogger(CrewDAO.class);
    @Override
    public List<Role> getAll(String WHERE) throws DaoException {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM roles " + WHERE + " ;";
        try (Connection connection = DataSourceCreator.getDataSource();
             Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(sql);
            LOG.trace("executeQuery("+sql+")");
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

    @Override
    public int create(Role role) throws DaoException{
        String sql = String.format(
                "insert INTO roles(name) values('%s');",role.getRole()
        );
        return executeUpdate(sql);
    }

    @Override
    public boolean update(Role role) throws DaoException{
        String sql = String.format(
                "UPDATE `roles` SET `name` = '%s' WHERE `roles`.`role_id` = %d",
                role.getRole(), role.getId()
        );
        return (0 < executeUpdate(sql));
    }

    @Override
    public boolean delete(Role role) throws DaoException{
        String sql = String.format(
                "DELETE FROM `roles` WHERE `roles`.`role_id` = %d;", role.getId()
        );
        return (0 < executeUpdate(sql));
    }


}
