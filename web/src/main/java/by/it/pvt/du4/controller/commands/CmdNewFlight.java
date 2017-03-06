package by.it.pvt.du4.controller.commands;

import by.it.pvt.du4.beans.Airport;
import by.it.pvt.du4.beans.Flight;
import by.it.pvt.du4.beans.Plane;
import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.dao.AirportsDao;
import by.it.pvt.du4.service.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;


class CmdNewFlight extends Action {
    private static final Logger LOG = LoggerFactory.getLogger(CmdNewFlight.class);
    @Override
    public Action execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        if ("POST".equalsIgnoreCase(request.getMethod())) {
            User user = (User) request.getSession().getAttribute("user");

            Flight flight = new Flight();
            flight.setUser_id(user);
            try {
                flight.setFlightCode(Form.getString(request, "code", Patterns.FLIGTH_CODE));
                flight.setCompany(Form.getString(request, "company", Patterns.PASSWORD));
                String timestampStr = request.getParameter("arrivalTime")+":00";
                Timestamp timestamp1 = Timestamp.valueOf(timestampStr);
                flight.setArrival_time(timestamp1);
                timestampStr = request.getParameter("departureTime")+":00";
                timestamp1 = Timestamp.valueOf(timestampStr);
                flight.setDeparture_time(timestamp1);

                AirportsDao airportsDao = null;//DaoFactory.getInstance().getDao(AirportsDAO.class);

                Plane plane = null;//DaoFactory.getInstance().getDao(PlaneDAO.class).get(Long.parseLong(Form.getString(request,"plane",Patterns.INT)));
                flight.setPlane_id(plane);

                Airport airport = null;//airportsDAO.get(Long.parseLong(Form.getString(request,"from",Patterns.INT)));
                flight.setFrom_id(airport);

                airport = null;//airportsDAO.get(Long.parseLong(Form.getString(request,"to",Patterns.INT)));
                flight.setTo_id(airport);

                flight.setCreateDate(new Date());

            }catch (Exception e) {
                e.printStackTrace();
                LOG.error("Invalid field format. "+e.toString());
                request.setAttribute(AttrMessages.msgError, "Invalid field format. "+e.toString());
                return null;
            }

//            try{
//                FlightService.getInstance().saveOrUpdate(flight);
//                LOG.trace("Create new flight"+flight);
//                return  Actions.INDEX.action;
//            } catch (ServiceException e){
//                LOG.error("Flight does not created."+e);
//                return  Actions.NEWFLIGHT.action;
//            }
        }else{
//            request.setAttribute("airports", DictionaryServiceUtil.getInstance().getAirports());
//            request.setAttribute("planes", DictionaryServiceUtil.getInstance().getPlanes());
        }
        return null;
    }
}