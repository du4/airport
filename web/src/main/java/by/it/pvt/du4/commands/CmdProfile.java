package by.it.pvt.du4.commands;

import by.it.pvt.du4.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class CmdProfile extends Action {
    @Override
    public Action execute(HttpServletRequest request, HttpServletResponse response) {
        Object o = request.getSession().getAttribute("user");
        if (o != null){
            if (o instanceof User){
                request.setAttribute("user",o);
            }
        }else {
            request.removeAttribute("user");
        }
        SessionAttrSesHelper.setRolesToAttribute(request);
        return null;
    }
}
