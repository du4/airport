package by.it.pvt.du4.servlets;


import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.commands.Action;
import by.it.pvt.du4.commands.Actions;
import by.it.pvt.du4.commands.SessionAttrSesHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

//@WebServlet(value = "/airport")
public class FrontController extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        updateSessionCash(request);
        setUserToAttribute(request);

        Action action = Actions.defineFrom(request);
        Action nextAction = action.execute(request, response);

        if (nextAction == null) {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(action.getJsp());
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("airport?command=" + nextAction);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        updateSessionCash(request);
        setUserToAttribute(request);

        Action action = Actions.defineFrom(request);
        action.execute(request, response);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(action.getJsp());

        requestDispatcher.forward(request, response);


    }

    private void updateSessionCash(HttpServletRequest request){
        SessionAttrSesHelper.setCommandToAttribute(request);
        SessionAttrSesHelper.setPermissionToAttribute(request);
    }

    private void setUserToAttribute(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");

        if (user != null) {
            request.setAttribute("curUser", "Session info: user.login=" + user.getLogin() +", role="+ user.getRole() + ", created at-" + new Timestamp(session.getLastAccessedTime()));
            request.setAttribute("user", user);
        } else {

//            Cookie[] cookies = request.getCookies();
//            //     Create map of cookies
//            if (cookies != null) {
//                Map<String, String> cookieMap = new HashMap();
//
//                for (Cookie cookie : cookies) {
//                    cookieMap.put(cookie.getName(), cookie.getValue());
//                }
//
//                String userHash = cookieMap.get("uid");
//
//                if (userHash != null && !userHash.isEmpty()){
//                    DAO dao = DAO.getDAO((String) request.getAttribute(FrontController.CSPATH));
//                    List<User> userList = dao.userDAO.getAll("");
//                    for (User u : userList) {
//                        if (u.hashCode() == Integer.valueOf(userHash)) {
//                            request.setAttribute("user", u);
//                            break;
//                        }
//                    }
//                }
            request.setAttribute("user", null);
        }
    }
}
