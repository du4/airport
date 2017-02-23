package by.it.pvt.du4.service.interfaces;

import by.it.pvt.du4.beans.Employee;
import by.it.pvt.du4.beans.Flight;
import by.it.pvt.du4.beans.FlightStr;
import by.it.pvt.du4.service.exceptions.ServiceException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IFlightService extends IService<Flight> {

    List<Employee> gerFlightCrew(Serializable id) throws ServiceException;

    List<FlightStr> getAllStringFlights(Map<String,String> flightQuery) throws ServiceException;

    Long getFlightsCount() throws ServiceException;


}
