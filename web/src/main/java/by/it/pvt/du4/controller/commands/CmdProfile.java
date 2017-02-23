package by.it.pvt.du4.controller.commands;

import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.service.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class CmdProfile extends Action {
    @Override
    public Action execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Object o = request.getSession().getAttribute("user");
        if (o != null){
            if (o instanceof User){
                request.setAttribute("user",o);
            }
        }else {
            request.removeAttribute("user");
        }
//        SessionAttrSesHelper.setRolesToAttribute(request);
        return null;
    }
}
