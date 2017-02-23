package by.it.pvt.du4.controller.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class CmdLogout extends Action {
    private static final Logger LOG = LoggerFactory.getLogger(CmdLogout.class);
    @Override
    public Action execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        if (request.getMethod().equalsIgnoreCase("post")){
            LOG.trace("Logout user = "+request.getAttribute("user"));
            request.removeAttribute("user");
            session.invalidate();
            return Actions.LOGIN.action;
        }
        return null;
    }
}
