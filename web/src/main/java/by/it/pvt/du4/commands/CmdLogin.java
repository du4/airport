package by.it.pvt.du4.commands;

import by.it.pvt.du4.DictionaryUtil;
import by.it.pvt.du4.UserService;
import by.it.pvt.du4.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

class CmdLogin extends Action {
    private static final Logger LOG = LoggerFactory.getLogger(CmdLogin.class);

    @Override
    public Action execute(HttpServletRequest request, HttpServletResponse response) {

        if (request.getMethod().equalsIgnoreCase("POST")) {
            User user = new User();
            try {
                user.setLogin(Form.getString(request, "login", Patterns.LOGIN));
                user.setPass(Form.getString(request, "pass", Patterns.PASSWORD));

                List<User> userList = UserService.getInstance().get(user);
                if (userList.size() > 0) {
                    request.setAttribute(AttrMessages.msgMessage, "User login - OK ");
                    addUserToSessionCookie(userList.get(0), request, response);
                    LOG.trace("User="+user+ "are authorized.");
                    return Actions.INDEX.action;
                } else {
                    LOG.error("Can't login user="+user);
                    throw new IllegalArgumentException("Wrong login or password");
                }
            } catch (Exception e) {
                LOG.error("Invalid field format. " + e.toString());
                request.setAttribute(AttrMessages.msgError, "Invalid field format. " + e.toString());
            }
        }
        return  null;
    }

    private void addUserToSessionCookie(User user, HttpServletRequest request, HttpServletResponse response){
//        Save user to Cookies
        Cookie cookie = new Cookie("uid", ""+user.hashCode());
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);
//        Save user to Session
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

    }
}