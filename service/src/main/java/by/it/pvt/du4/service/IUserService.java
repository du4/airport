package by.it.pvt.du4.service;

import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.service.exceptions.ServiceException;
import by.it.pvt.du4.service.exceptions.ValidationException;

public interface IUserService extends IService<User> {

    User getByLoginAndPassword(User user) throws ValidationException, ServiceException;

}
