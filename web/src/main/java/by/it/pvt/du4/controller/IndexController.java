package by.it.pvt.du4.controller;

import by.it.pvt.du4.service.exceptions.ServiceException;
import by.it.pvt.du4.service.interfaces.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.ws.RequestWrapper;

@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IFlightService flightService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String welcomePage(ModelMap model) {
        populatePageName(model);
        try {
            model.put("flightStr", flightService.getAllStringFlights(null));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "home";
    }

    @RequestMapping(value = "/login-fail", method = RequestMethod.GET)
    @RequestWrapper
    public String loginFail(ModelAndView model, @RequestParam(value = "login-fail") String error ) {
        if ("error".equals(error)) {
            model.addObject("error", "Authentication error");
        }

        return "home";
    }

    private void populatePageName(ModelMap model) {
        model.addAttribute("currentPageName", "home");
    }
}



