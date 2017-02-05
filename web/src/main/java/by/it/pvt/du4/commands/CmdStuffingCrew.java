package by.it.pvt.du4.commands;

import by.it.pvt.du4.FlightService;
import by.it.pvt.du4.beans.Airhostess;
import by.it.pvt.du4.beans.Employee;
import by.it.pvt.du4.beans.Pilot;
import by.it.pvt.du4.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


class CmdStuffingCrew extends Action {
    private static final Logger LOG = LoggerFactory.getLogger(CmdStuffingCrew.class);
    @Override
    public Action execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            List<Pilot>pilots = new ArrayList<>();
            List<Airhostess>airhostesses = new ArrayList<>();
            List<Employee> crew = FlightService.getInstance().gerFlightCrew(request.getParameter("flight_id"));
            crew.forEach( c -> {
                        if (c instanceof Pilot) {
                            pilots.add((Pilot) c);
                        }
                        if (c instanceof Airhostess) {
                            airhostesses.add((Airhostess)c);
                        }
                    }
            );
            request.setAttribute("additionDropDoun", 2);
            request.setAttribute("crewPilots",pilots);
            request.setAttribute("crewAirhostsses", airhostesses);
            SessionAttrSesHelper.setPilotsToAttribute(request);
            SessionAttrSesHelper.setAirhostessToAttribute(request);

        }else{// POST
            try {
//                crew.setPilot1_id(Integer.parseInt(Form.getString(request,"pilot1", Patterns.INT)));
//                crew.setPilot2_id(Integer.parseInt(Form.getString(request,"pilot2", Patterns.INT)));
//                crew.setAirhostess1_id(Integer.parseInt(Form.getString(request,"airhostess1", Patterns.INT)));
//                crew.setAirhostess2_id(Integer.parseInt(Form.getString(request,"airhostess2", Patterns.INT)));
//                if (crew.getPilot1_id() == crew.getPilot2_id()){
//                    LOG.error("pilot1 = pilot2");
//                    throw new IllegalArgumentException("Select different pilots");
//                }
//                if (crew.getAirhostess1_id() == crew.getAirhostess2_id()){
//                    LOG.error("Airhostess1 = airhostess2");
//                    throw new IllegalArgumentException("Select different airhostesses");
//                }

            } catch (Exception e) {
                e.printStackTrace();
                LOG.error("Invalid field format.");
//                request.setAttribute(AttrMessages.msgError, "Invalid field format. "+e.toString());
                return null;
            }
//            if (
//                    CrewService.getInstance().create(crew);
//                            >0){
//                LOG.trace("New crew_id is created." + crew);
//            } else {
//                LOG.trace("Crew does not created.");
//            }
        }
        return null;
    }
}
