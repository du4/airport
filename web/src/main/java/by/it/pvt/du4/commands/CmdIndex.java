package by.it.pvt.du4.commands;

import by.it.pvt.du4.FlightService;
import by.it.pvt.du4.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class CmdIndex extends Action {
    private  static final Logger LOG = LoggerFactory.getLogger(CmdIndex.class);

    @Override
    public Action execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int startNumber = 0;
        String flightQuery ="";

        if ("post".equalsIgnoreCase(request.getMethod())) {
            try {
                FlightQueryStrBuilder queryStrBuilder = new FlightQueryStrBuilder();
                if (request.getParameter("from_id") != null && request.getParameter("to_id")!=null) {
                    if (request.getParameter("from_id").equals(request.getParameter("to_id"))) {
                        throw new IllegalArgumentException("Destination can't be equals to_id departure.");
                    }
                }
                queryStrBuilder.appendIntParam("fromPort", "=", request.getParameter("from_id"));
                queryStrBuilder.appendIntParam("toPort", "=", request.getParameter("to_id"));
                queryStrBuilder.appendStrParam("DATE(departure_time)", "=", request.getParameter("departureTime"));
                queryStrBuilder.appendStrParam("DATE(arrival_time)", "=", request.getParameter("arrivalTime"));
                LOG.trace(queryStrBuilder.toString());
                flightQuery = queryStrBuilder.getQuery();
            } catch (Exception e) {
                e.printStackTrace();
                LOG.error(e.getMessage());
                return null;
            }
        }
        request.setAttribute("airports", FlightService.gerInstance().getAirports());
//        List<FlightStr> flightStrs = FlightService.gerInstance().getAll(flightQuery);
//        for (FlightStr flight : flightStrs) {
//            flight.setViewNumber(++startNumber);
//        }
//        request.setAttribute("flights", flightStrs);
//        SessionAttrSesHelper.setAirportsToAttribute(request);
        return null;
    }
}
