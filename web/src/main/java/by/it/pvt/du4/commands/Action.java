package by.it.pvt.du4.commands;

import by.it.pvt.du4.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract public class Action {

    /**
     * Execute command from_id request
     * @param request
     * @param response
     * @return Action
     * @throws ServiceException
     */
    public abstract Action execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

    @Override
    public String toString() {
        String name=this.getClass().getSimpleName();
        name=name.replace("Cmd","");
        return name;
    }

    /**
     * return path to_id jsp view
     * @return String
     */
    public String getJsp(){
        String name = "/" + this.toString().toLowerCase()+".jsp";
        if (!name.equalsIgnoreCase("/index.jsp")){
            name = "/WEB-INF/views" + name;
        }
        return   name;
    }
}
