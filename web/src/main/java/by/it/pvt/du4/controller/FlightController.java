package by.it.pvt.du4.controller;

import by.it.pvt.du4.beans.Airport;
import by.it.pvt.du4.beans.Flight;
import by.it.pvt.du4.beans.Plane;
import by.it.pvt.du4.service.exceptions.ServiceException;
import by.it.pvt.du4.service.interfaces.IAirportService;
import by.it.pvt.du4.service.interfaces.IFlightService;
import by.it.pvt.du4.service.interfaces.IPlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.ws.RequestWrapper;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private IFlightService flightService;
    @Autowired
    private IPlaneService planeService;
    @Autowired
    private IAirportService airportService;

    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String flightPage(ModelMap map) {
        populatePageName(map);
        map.addAttribute("currentPageName", "newFlight");
        try {
            map.addAttribute("flight", new Flight());
            map.addAttribute("planes", planeService.getAll(Plane.class));
            map.addAttribute("airports", airportService.getAll(Airport.class));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "addflight";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addFlight(ModelMap map, Flight flight) {
        populatePageName(map);
        if (flight != null) {
            try {
                flightService.create(flight);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
    }


    private void populatePageName(ModelMap model) {
        model.addAttribute("currentPageName", "newFlight");
    }
}



