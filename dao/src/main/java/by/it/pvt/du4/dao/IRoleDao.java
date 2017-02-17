package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.dao.exceptions.DaoException;

public interface IRoleDao extends IDao<Role> {

    Role getByName(String name) throws DaoException;

}
