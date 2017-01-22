package by.it.pvt.du4.servlets;

import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.commands.Action;
import by.it.pvt.du4.commands.Actions;
import by.it.pvt.du4.commands.SessionAttrSesHelper;
import by.it.pvt.du4.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FrontController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(FrontController.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Action action;
        Action nextAction = null;
        try {
            updateHttpSessionCash(request);
            setUserToAttribute(request);
            action = Actions.defineFrom(request);
            LOG.trace("ActionPage="+action.getJsp());
            nextAction = action.execute(request, response);
        } catch (ServiceException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
            action = Actions.getErrorAction();
        }
        if (nextAction == null) {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(action.getJsp());
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("airport?command=" + nextAction);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Action action;
        try {
            updateHttpSessionCash(request);
            setUserToAttribute(request);
            action = Actions.defineFrom(request);
            action.execute(request, response);
            LOG.trace("ActionPage="+action.getJsp());

        } catch (ServiceException e) {
            LOG.error(""+e);
            e.printStackTrace();
            action = Actions.getErrorAction();
        }

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(action.getJsp());
        requestDispatcher.forward(request, response);

    }

    /**
     * Store commandList & permissionList to_id current Session if ifs absent
     * @param request
     * @throws ServiceException
     */
    private void updateHttpSessionCash(HttpServletRequest request) throws ServiceException {
        SessionAttrSesHelper.setCommandToAttribute(request);
        SessionAttrSesHelper.setCommandToAttribute(request);
        SessionAttrSesHelper.setPermissionToAttribute(request);
    }

    /**
     * Get user_id from_id session if its not null sore to_id request attribute.
     * Else store null user_id to_id request attribute
     * @param request
     */
    private void setUserToAttribute(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user_id");

        if (user != null) {
            request.setAttribute("curUser", "Session info: user_id.login=" + user.getLogin() +", role="+ user.getRole_id());
            request.setAttribute("user_id", user);
            LOG.trace("Set user_id from_id session to_id request attribute = "+user);
        } else {
            request.setAttribute("user_id", null);
            LOG.trace("Unauthorized user_id.");
        }
    }
}
