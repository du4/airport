package by.it.pvt.du4.servlets;

import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.commands.Action;
import by.it.pvt.du4.commands.Actions;
import by.it.pvt.du4.commands.SessionAttrSesHelper;
import by.it.pvt.du4.exceptions.ServiceException;
import by.it.pvt.du4.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

//    /**
//     * Generate sample data to tables
//     * @throws ServletException
//     */
//    @Override
//    public void init() throws ServletException {
//        try {
//           ServiceDataGeneratorUtil.getInstance().generateData();
//        } catch (DaoException | ServiceException e) {
//            LOG.error(""+e);
//            e.printStackTrace();
//        }
//    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Action action;
        Action nextAction = null;
        try {
            updateHttpSessionCash(request);
            setUserToAttribute(request);
            action = Actions.defineFrom(request);
            LOG.trace("ActionPage="+ action.getJsp());
            nextAction = action.execute(request, response);
        } catch (ServiceException e) {
            LOG.error(""+e);
            e.printStackTrace();
            action = Actions.getErrorAction();
        }
        if (nextAction == null) {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(action.getJsp());
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("airport?action=" + nextAction);
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
            LOG.trace("ActionPage="+ action.getJsp());

        } catch (ServiceException e) {
            LOG.error(""+e);
            e.printStackTrace();
            action = Actions.getErrorAction();
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(action.getJsp());
        requestDispatcher.forward(request, response);
    }

    /**
     * Store commandList & permissionList to current Session if it exist
     * @param request
     * @throws ServiceException
     */
    private void updateHttpSessionCash(HttpServletRequest request) throws ServiceException {
        Session hs = HibernateUtil.getHibernateUtil().getHibernateSession();
        Transaction t =  hs.beginTransaction();
        SessionAttrSesHelper.setCommandToAttribute(request);
        SessionAttrSesHelper.setPermissionToAttribute(request);
//        SessionAttrSesHelper.setPlanesToAttribute(request);
        t.commit();
        hs.flush();
    }

    /**
     * Get user_id from_id session if its not null sore to_id request attribute.
     * Else store null user_id to_id request attribute
     * @param request
     */
    private void setUserToAttribute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        if (user != null) {
            request.setAttribute("curUser", "Session info: user.login=" + user.getLogin() +", role="+ user.getRole().getName());
            request.setAttribute("user", user);
            LOG.trace("Set user from session to request attribute = " + user);
        } else {
            request.setAttribute("user", null);
            LOG.trace("Save user=null to http session.");
        }
    }
}
