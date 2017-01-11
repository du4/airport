package by.it.pvt.du4.commands;

import by.it.pvt.du4.DictionaryService;
import by.it.pvt.du4.beans.*;
import by.it.pvt.du4.dao.DAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.DesktopIconUI;
import java.util.List;

public class SessionAttrSesHelper {

    @SuppressWarnings("unchecked")
    static void setRolesToAttribute(HttpServletRequest request){
        Object o = request.getSession().getAttribute("roles");
        if (o != null) {
            if (o instanceof List) {
                List<Role> roleList = (List<Role>) o;
                request.setAttribute("roles", roleList);
            }
        }else {
            List<Role> roles = DictionaryService.getInstance().getRoles();
            request.setAttribute("roles",roles);
            request.getSession().setAttribute("roles",roles);
        }
    }
    @SuppressWarnings("unchecked")
    static void setPilotsToAttribute(HttpServletRequest request){
        Object o = request.getSession().getAttribute("pilots");
        if (o != null) {
            if (o instanceof List) {
                List<Pilot> pilotList = (List<Pilot>) o;
                request.setAttribute("pilots", pilotList);
            }
        }else {
            List<Pilot> roles = DictionaryService.getInstance().getPilots();
            request.setAttribute("pilots",roles);
            request.getSession().setAttribute("pilots",roles);
        }
    }
    @SuppressWarnings("unchecked")
    static void setAirhostessToAttribute(HttpServletRequest request){
        Object o = request.getSession().getAttribute("airhostesses");
        List<Airhostess> airhostesses;
        if (o != null) {
            if (o instanceof List) {
                airhostesses = (List<Airhostess>) o;
                request.setAttribute("airhostesses", airhostesses);
            }
        }else {
            airhostesses = DictionaryService.getInstance().getAirhostesses();
            request.setAttribute("airhostesses",airhostesses);
            request.getSession().setAttribute("airhostesses",airhostesses);
        }
    }
    @SuppressWarnings("unchecked")
    static void setAirportsToAttribute(HttpServletRequest request){
        Object o = request.getSession().getAttribute("airports");
        List<Airport> airports;
        if (o != null) {
            if (o instanceof List) {
                airports = (List<Airport>) o;
                request.setAttribute("airports", airports);
            }
        }else {
            airports = DictionaryService.getInstance().getAirports();
            request.setAttribute("airports",airports);
            request.getSession().setAttribute("airports",airports);
        }
    }
    @SuppressWarnings("unchecked")
    static void setPlanesToAttribute(HttpServletRequest request){
        Object o = request.getSession().getAttribute("planes");
        List<Plane> planes;
        if (o != null) {
            if (o instanceof List) {
                planes = (List<Plane>) o;
                request.setAttribute("planes", planes);
            }
        }else {
            planes = DictionaryService.getInstance().getPlanes();
            request.setAttribute("planes", planes);
            request.getSession().setAttribute("planes", planes);
        }
    }
    @SuppressWarnings("unchecked")
    static void setCrewsToAttribute(HttpServletRequest request){
        Object o = request.getSession().getAttribute("crews");
        List<Crew> crews;
        if (o != null) {
            if (o instanceof List) {
                crews = (List<Crew>) o;
                request.setAttribute("crews", crews);
            }
        }else {
            crews = DictionaryService.getInstance().getCrews();
            request.setAttribute("crews", crews);
            request.getSession().setAttribute("crews", crews);
        }
    }
    @SuppressWarnings("unchecked")
    public static void setCommandToAttribute(HttpServletRequest request){
        Object o = request.getSession().getAttribute("commands");
        List<Command> commands;
        if (o != null) {
            if (o instanceof List) {
                commands = (List<Command>) o;
                request.setAttribute("commands", commands);
            }
        }else {
            commands = DictionaryService.getInstance().getCommands();
            request.setAttribute("commands", commands);
            request.getSession().setAttribute("commands", commands);
        }
    }
    @SuppressWarnings("unchecked")
    public static void setPermissionToAttribute(HttpServletRequest request){
        Object o = request.getSession().getAttribute("permissions");
        List<Permission> permissions;
        if (o != null) {
            if (o instanceof List) {
                permissions = (List<Permission>) o;
                request.setAttribute("permissions", permissions);
            }
        }else {
            permissions = DictionaryService.getInstance().getPermissions();
            request.setAttribute("permissions", permissions);
            request.getSession().setAttribute("permissions", permissions);
        }
    }
}
