package by.it.pvt.du4.commands;

import by.it.pvt.du4.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


class CmdNewFlight extends Action {
    private static final Logger LOG = LoggerFactory.getLogger(CmdNewFlight.class);
    @Override
    public Action execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

//        if (request.getMethod().equalsIgnoreCase("POST")) {
//            User user = (User) request.getSession().getAttribute("user_id");
//            if (user == null){
//                LOG.error("Permission deny, user_id is not authorized.");
//                request.setAttribute(AttrMessages.msgError, "Permission deny, user_id is not authorized.");
//                return Actions.LOGIN.action;
//            }
//            Flight flight = new Flight();
//
//            flight.setUser_id(user.getId());
//
//            try {
//                flight.setFlightCode(Form.getString(request, "code", Patterns.FLIGTH_CODE));
//                flight.setCompany(Form.getString(request, "company", Patterns.PASSWORD));
//
//                String timestampStr = request.getParameter("arrivalTime")+":00";
//                Timestamp timestamp1 = Timestamp.valueOf(timestampStr);
//                flight.setArrival_time(timestamp1 );
//
//                timestampStr = request.getParameter("departureTime")+":00";
//                timestamp1 = Timestamp.valueOf(timestampStr);
//                flight.setDeparture_time(timestamp1 );
//
//                flight.setPlane_id(Integer.parseInt(Form.getString(request,"plane_id",Patterns.INT)));
//                flight.setFrom_id(Integer.parseInt(Form.getString(request,"from_id",Patterns.INT)));
//                flight.setTo_id(Integer.parseInt(Form.getString(request,"to_id",Patterns.INT)));
//                flight.setCrew_id(Integer.parseInt(Form.getString(request, "crew_id", Patterns.INT)));
//                if (flight.getFrom_id() == flight.getTo_id()){
//                    LOG.error("Destination can't be equals to_id Arrival");
//                    throw new IllegalArgumentException("Destination can't be equals to_id Arrival");
//                }
//                if (flight.getDeparture_time().getTime()  >= flight.getArrival_time().getTime()){
//                    LOG.error("Arrival time must be grater then Destination time");
//                    throw new IllegalArgumentException("Arrival time must be grater then Destination time");
//                }
//            }catch (Exception e) {
//                e.printStackTrace();
//                LOG.error("Invalid field format. "+e.toString());
//                request.setAttribute(AttrMessages.msgError, "Invalid field format. "+e.toString());
//                return null;
//            }
//
//            if(FlightService.gerInstance().create(flight)>0){
//                LOG.trace("Create new flight"+flight);
//                return  Actions.INDEX.action;
//            } else {
//                LOG.error("Flight does not created.");
//                return  Actions.NEWFLIGHT.action;
//            }
//        }else{
//            SessionAttrSesHelper.setAirportsToAttribute(request);
//            SessionAttrSesHelper.setPlanesToAttribute(request);
//            SessionAttrSesHelper.setCrewsToAttribute(request);
//        }
        return null;
    }
}
