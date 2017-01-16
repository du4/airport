package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.User;
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

public class UserDAO extends AbstractDAO implements IDAO<User> {
    private static final Logger LOG = LoggerFactory.getLogger(CrewDAO.class);

    @Override
    public User read(int id) throws DaoException {
        List<User> users =  getAll("WHERE user_id=" + id + " LIMIT 0,1");
        return (users.size() > 0) ? users.get(0) : null;
    }

    @Override
    public int create(User entity) throws DaoException{
        String sql = String.format("INSERT INTO users(login, pass, role, email) VALUES('%s','%s','%s','%s');",
                entity.getLogin(), entity.getPass(), entity.getRole(), entity.getEmail());
        return executeUpdate(sql);
    }

    @Override
    public boolean update(User entity) throws DaoException{
        String sql = String.format("UPDATE `users` SET `login`='%s', `pass`='%s', `role`='%s', `email`='%s' WHERE `users`.`user_id` = %d;",
                entity.getLogin(), entity.getPass(), entity.getRole(), entity.getEmail(), entity.getId());
        return (0<executeUpdate(sql));
    }

    @Override
    public boolean delete(User entity) throws DaoException{
        String sql = String.format("DELETE FROM `users` WHERE  `users`.`user_id`=%d;", entity.getId());
//        executeUpdate(sql);
        return (0<executeUpdate(sql));
    }

    @Override
    public List<User> getAll(String WhereAndOrder) throws DaoException{
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users " + WhereAndOrder + ";";
        try (Connection connection = DataSourceCreator.getDataSource();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            LOG.trace("executeQuery("+sql+")");
            while (resultSet.next()){
                users.add(new User(resultSet.getInt("user_id"),resultSet.getString("login"),
                        resultSet.getString("email"),resultSet.getString("pass"),resultSet.getInt("role")));
            }

        } catch (SQLException e) {
            LOG.error("ERROR"+e.getMessage());
            e.printStackTrace();
            throw new DaoException(e);
        }
        return users;
    }
}
