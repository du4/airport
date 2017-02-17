package by.it.pvt.du4.dao;

import by.it.pvt.du4.beans.Employee;
import by.it.pvt.du4.beans.Flight;
import by.it.pvt.du4.beans.FlightStr;
import by.it.pvt.du4.dao.exceptions.DaoException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IFlightDao extends IDao<Flight> {

    List<FlightStr> getByFilter(Map<String, String> flightQuery) throws DaoException;

    List<Employee> getFlightCrew(Serializable id) throws DaoException;

    Long getCount() throws DaoException;


}
