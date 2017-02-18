package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.dao.exceptions.DaoException;

public interface IUserDao<T> extends IDao<T> {

    User getByLoginAndPassword(User user) throws DaoException;

}
