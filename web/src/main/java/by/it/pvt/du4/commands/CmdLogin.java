package by.it.pvt.du4.commands;

import by.it.pvt.du4.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class CmdLogin extends Action {
    private static final Logger LOG = LoggerFactory.getLogger(CmdLogin.class);

    @Override
    public Action execute(HttpServletRequest request, HttpServletResponse response) {

        if ("POST".equalsIgnoreCase(request.getMethod())) {
            User user = new User();
            try {
                user.setLogin(Form.getString(request, "login", Patterns.LOGIN));
                user.setPass(Form.getString(request, "pass", Patterns.PASSWORD));
                user = null;//UserService.getInstance().getByLoginAndPassword(user);
                request.setAttribute(AttrMessages.msgMessage, "User login - OK ");
                addUserToSessionCookie(user, request, response);
                LOG.trace("User="+user+ "are authorized.");
                return Actions.INDEX.action;
            } catch (Exception e) {
                LOG.error("Invalid field format. " + e.toString());
                request.setAttribute(AttrMessages.msgError, "Invalid field format. " + e.toString());
            }
        }
        return  null;
    }

    private void addUserToSessionCookie(User user, HttpServletRequest request, HttpServletResponse response){
//        Save user to_id Cookies
        Cookie cookie = new Cookie("uid", ""+user.hashCode());
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);
//        Save user to_id Session
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

    }
}