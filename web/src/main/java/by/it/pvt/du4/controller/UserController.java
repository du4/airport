
package by.it.pvt.du4.controller;

import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.service.exceptions.ServiceException;
import by.it.pvt.du4.service.interfaces.IRoleService;
import by.it.pvt.du4.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    public static final String MAIN = "user/main";
    private static List<User> userList = new ArrayList();

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        try {
            model.put("currentPageName", "usermanagement");
            model.put("users", userService.getAll(User.class));
            model.put("roles", roleService.getAll(Role.class));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return MAIN;
    }

    //@PreAuthorize("APP_ROLE")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String userInfo() {

        return "user/info";
    }

    @RequestMapping(value = "/add-person", method = {RequestMethod.GET, RequestMethod.POST})
    public String addUser(ModelMap model, @ModelAttribute User person) {
        userList.add(person);
        model.put("persons", userList);
        model.put("person", person);

        return MAIN;
    }

    @RequestMapping(value = "/delete-person", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteUser(ModelMap model, @ModelAttribute("person") User person,
                               @RequestParam(value = "id", defaultValue = "") Integer id) {

        return MAIN;
    }
}
