package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.pool.PoolCreator;
import by.it.pvt.du4.dao.exceptions.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO implements IDAO<User> {
    private static final Logger LOG = LoggerFactory.getLogger(CrewDAO.class);

    @Override
    public int create(User entity) throws DaoException{
        int result = -1;
        try(Connection connection =  PoolCreator.getConnectionFromPool();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(login, pass, role, email) VALUES(?,?,?,?);")
        ){
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPass());
            preparedStatement.setInt(3, entity.getRole());
            preparedStatement.setString(4, entity.getEmail());
            result = preparedStatement.executeUpdate();
            LOG.info("Create user="+entity);
        }catch (SQLException e) {
            LOG.error(""+e);
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean update(User entity) throws DaoException{
        int result = -1;
        try(Connection connection =  PoolCreator.getConnectionFromPool();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `users` SET `login`=?, `pass`=?, `role`=?, `email`=? WHERE `users`.`user_id` = ?;")
        ){
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPass());
            preparedStatement.setInt(3, entity.getRole());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setInt(5,entity.getId());
            result = preparedStatement.executeUpdate();
            LOG.info("Update user="+entity);
        }catch (SQLException e) {
            LOG.error(""+e);
            throw new DaoException(e);
        }
        return (result > 0);
    }

    @Override
    public boolean delete(User entity) throws DaoException{
        int result = -1;
        try(Connection connection =  PoolCreator.getConnectionFromPool();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `users` WHERE  `users`.`user_id`=?;")
        ){
            preparedStatement.setInt(1, entity.getId());
            result = preparedStatement.executeUpdate();
            LOG.info("Delete user="+entity);
        }catch (SQLException e) {
            LOG.error(""+e);
            throw new DaoException(e);
        }
        return (result > 0);
    }

    @Override
    public User read(int id) throws DaoException {
        List<User> users =  getAll("WHERE user_id=" + id + " LIMIT 0,1");
        return (users.size() > 0) ? users.get(0) : null;
    }

    @Override
    public List<User> getAll(String WhereAndOrder) throws DaoException{
        List<User> users = new ArrayList<>();
        try(Connection connection =  PoolCreator.getConnectionFromPool();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users " + WhereAndOrder +" ;")
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                users.add(new User(resultSet.getInt("user_id"),resultSet.getString("login"),
                        resultSet.getString("email"),resultSet.getString("pass"),resultSet.getInt("role")));
            }
            LOG.info("Get users(count="+users.size()+")");
        }catch (SQLException e) {
            LOG.error(""+e);
            throw new DaoException(e);
        }
        return users;
    }
}
