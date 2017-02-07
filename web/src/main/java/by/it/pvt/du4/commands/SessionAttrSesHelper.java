package by.it.pvt.du4.commands;

import by.it.pvt.du4.DictionaryServiceUtil;
import by.it.pvt.du4.beans.*;
import by.it.pvt.du4.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SessionAttrSesHelper {

    @SuppressWarnings("unchecked")
    public static void setRolesToAttribute(HttpServletRequest request) throws ServiceException {
        Object o = request.getSession().getAttribute("roles");
        if (o != null) {
            if (o instanceof List) {
                List<Role> roleList = (List<Role>) o;
                request.setAttribute("roles", roleList);
            }
        }else {
            List<Role> roles = DictionaryServiceUtil.getInstance().getRoles();
            request.setAttribute("roles",roles);
            request.getSession().setAttribute("roles",roles);
        }
    }
    @SuppressWarnings("unchecked")
    static void setPilotsToAttribute(HttpServletRequest request) throws ServiceException {
        Object o = request.getSession().getAttribute("pilots");
        if (o != null) {
            if (o instanceof List) {
                List<Pilot> pilotList = (List<Pilot>) o;
                request.setAttribute("pilots", pilotList);
            }
        }else {
            List<Pilot> roles = DictionaryServiceUtil.getInstance().getPilots();
            request.setAttribute("pilots",roles);
            request.getSession().setAttribute("pilots",roles);
        }
    }
    @SuppressWarnings("unchecked")
    static void setAirhostessToAttribute(HttpServletRequest request) throws ServiceException {
        Object o = request.getSession().getAttribute("airhostesses");
        List<Airhostess> airhostesses;
        if (o != null) {
            if (o instanceof List) {
                airhostesses = (List<Airhostess>) o;
                request.setAttribute("airhostesses", airhostesses);
            }
        }else {
            airhostesses = DictionaryServiceUtil.getInstance().getAirhostesses();
            request.setAttribute("airhostesses",airhostesses);
            request.getSession().setAttribute("airhostesses",airhostesses);
        }
    }
    @SuppressWarnings("unchecked")
    public static void setAirportsToAttribute(HttpServletRequest request) throws ServiceException {
        Object o = request.getSession().getAttribute("airports");
        List<Airport> airports;
        if (o != null) {
            if (o instanceof List) {
                airports = (List<Airport>) o;
                request.setAttribute("airports", airports);
            }
        }else {
            airports = DictionaryServiceUtil.getInstance().getAirports();
            request.setAttribute("airports",airports);
            request.getSession().setAttribute("airports",airports);
        }
    }
    @SuppressWarnings("unchecked")
    public static void setPlanesToAttribute(HttpServletRequest request) throws ServiceException {
        Object o = request.getSession().getAttribute("planes");
        List<Plane> planes;
        if (o != null) {
            if (o instanceof List) {
                planes = (List<Plane>) o;
                request.setAttribute("planes", planes);
            }
        }else {
            planes = DictionaryServiceUtil.getInstance().getPlanes();
            request.setAttribute("planes", planes);
            request.getSession().setAttribute("planes", planes);
        }
    }

    public static void setCommandToAttribute(HttpServletRequest request) throws ServiceException {
        List<Command> commands = DictionaryServiceUtil.getInstance().getCommands();
        request.setAttribute("commands", commands);
    }

    public static void setPermissionToAttribute(HttpServletRequest request) throws ServiceException {
        List<Permission> permissions = DictionaryServiceUtil.getInstance().getPermissions();
        request.setAttribute("permissions", permissions);
    }
}
