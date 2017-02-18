package by.it.pvt.du4.service.interfaces;


import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.service.exceptions.ServiceException;

public interface IRoleService extends IService<Role> {
    Role getByName(String name) throws ServiceException;
}
