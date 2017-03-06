package by.it.pvt.du4.controller;

import by.it.pvt.du4.beans.Airport;
import by.it.pvt.du4.beans.Flight;
import by.it.pvt.du4.beans.Plane;
import by.it.pvt.du4.service.exceptions.ServiceException;
import by.it.pvt.du4.service.interfaces.IAirportService;
import by.it.pvt.du4.service.interfaces.IFlightService;
import by.it.pvt.du4.service.interfaces.IPlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IFlightService flightService;


    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String welcomePage(ModelMap map) {
        populatePageName(map);
        try {
            map.put("flightStr", flightService.getAllStringFlights(null));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "home";
    }

    private void populatePageName(ModelMap map) {
        map.addAttribute("currentPageName", "home");
    }
}



