package by.it.pvt.du4.commands;

import by.it.pvt.du4.FlightService;
import by.it.pvt.du4.beans.*;
import by.it.pvt.du4.dao.DAO;
import by.it.pvt.du4.exceptions.ServiceException;
import by.it.pvt.du4.servlets.HibernateSessionFilter;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.Session;
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

        if (request.getMethod().equalsIgnoreCase("POST")) {
            User user = (User) request.getSession().getAttribute("user");
//            if (user == null){
//                LOG.error("Permission deny, user_id is not authorized.");
//                request.setAttribute(AttrMessages.msgError, "Permission deny, user_id is not authorized.");
//                return Actions.LOGIN.action;
//            }

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

                Plane plane =  DAO.getDAO().planeDAO.get(Long.parseLong(Form.getString(request,"plane",Patterns.INT)));
                flight.setPlane_id(plane);

                Airport airport = DAO.getDAO().airportsDAO.get(Long.parseLong(Form.getString(request,"from",Patterns.INT)));
                flight.setFrom_id(airport);

                airport = DAO.getDAO().airportsDAO.get(Long.parseLong(Form.getString(request,"to",Patterns.INT)));
                flight.setTo_id(airport);

                Crew crew = DAO.getDAO().crewDAO.get(Long.parseLong(Form.getString(request, "crew", Patterns.INT)));
                flight.setCrew(crew);

//                if (flight.getFrom_id() == flight.getTo_id()){
//                    LOG.error("Destination can't be equals to_id Arrival");
//                    throw new IllegalArgumentException("Destination can't be equals to_id Arrival");
//                }
//                if (flight.getDeparture_time().getTime()  >= flight.getArrival_time().getTime()){
//                    LOG.error("Arrival time must be grater then Destination time");
//                    throw new IllegalArgumentException("Arrival time must be grater then Destination time");
//                }
                flight.setCreateDate(new Date());

            }catch (Exception e) {
                e.printStackTrace();
                LOG.error("Invalid field format. "+e.toString());
                request.setAttribute(AttrMessages.msgError, "Invalid field format. "+e.toString());
                return null;
            }

            try{
                FlightService.gerInstance().create(flight);
                LOG.trace("Create new flight"+flight);
                return  Actions.INDEX.action;
            } catch (ServiceException e){
                LOG.error("Flight does not created."+e);
                return  Actions.NEWFLIGHT.action;
            }
        }else{
            SessionAttrSesHelper.setAirportsToAttribute(request);
            SessionAttrSesHelper.setPlanesToAttribute(request);
            SessionAttrSesHelper.setCrewsToAttribute(request);
        }
        return null;
    }
}
