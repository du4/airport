package by.it.pvt.du4.controller.commands;

import by.it.pvt.du4.beans.FlightStr;
import by.it.pvt.du4.service.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CmdIndex extends Action {
    private  static final Logger LOG = LoggerFactory.getLogger(CmdIndex.class);
    private static int pageSize = 3;
    private static int pageCount = 0;
    private static long startIndex = 1;
    private static int activePageIndex = 1;
    @Override
    public Action execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String flightQuery ="";
        Map<String, String> params = new HashMap<>();

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

                params.put("from", request.getParameter("from"));
                params.put("to",request.getParameter("to"));
//                queryStrBuilder.appendStrParam("DATE(departure_time)", "=", request.getParameter("departureTime"));
//                queryStrBuilder.appendStrParam("DATE(arrival_time)", "=", request.getParameter("arrivalTime"));
                LOG.trace(queryStrBuilder.toString());
                flightQuery = queryStrBuilder.getQuery();
            } catch (Exception e) {
                e.printStackTrace();
                LOG.error(e.getMessage());
                return null;
            }
        }

        String ps = request.getParameter("pageSize");
        String api = request.getParameter("activePageIndex");

        if (api == null ) {
            api = Integer.toString(activePageIndex);
            ps = Integer.toString(pageSize);
        }
//        PAGINATION
        Long fCount = null;// FlightService.getInstance().getFlightsCount();
        startIndex = (Integer.parseInt(api)-1) * Integer.parseInt(ps);
        pageSize = Integer.parseInt(ps);

        params.put("pageSize", ps);
        params.put("startIndex", Long.toString(startIndex));
        List<FlightStr> flightStr = null;// FlightService.getInstance().getAllStringFlights(params);

        request.setAttribute("pageSize", pageSize);
        pageCount = (int)Math.ceil((float)(fCount)/pageSize);
        request.setAttribute("pageCount", pageCount);
        activePageIndex = Integer.parseInt(api);
        request.setAttribute("activePageIndex", activePageIndex);
//PAGINATION
        request.setAttribute("flightStr", flightStr);
        request.setAttribute("airports", null);//DictionaryServiceUtil.getInstance().getAirports());
        return null;
    }
}
