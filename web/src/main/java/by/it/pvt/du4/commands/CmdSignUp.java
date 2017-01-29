package by.it.pvt.du4.commands;

import by.it.pvt.du4.UserService;
import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;

class CmdSignUp extends Action {
    private static final Logger LOG = LoggerFactory.getLogger(CmdSignUp.class);
    @Override
    public Action execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        if ("POST".equalsIgnoreCase(request.getMethod())) {
            User user = new User();
            try {
                user.setLogin(Form.getString(request, "login", Patterns.LOGIN));
                user.setEmail(Form.getString(request, "email", Patterns.EMAIL));
//               need to realize on jsp by javaScript
//                if (!Form.getString(request, "pass", Patterns.PASSWORD).equals(Form.getString(request, "passConfirm", Patterns.PASSWORD))){
//                    LOG.error("Passwords don't match");
//                    throw  new IllegalArgumentException("Passwords don't match");
//                }
                user.setPass(Form.getString(request, "pass", Patterns.PASSWORD));
                user.setCreatedDate(new Date());

            } catch (Exception e) {
                LOG.error("Invalid field format. " + e.toString());
                request.setAttribute(AttrMessages.msgError, "Invalid field format. " + e.toString());
                return null;
            }

//            if (
                    UserService.getInstance().create(user);
//                            > 0) {
                request.setAttribute(AttrMessages.msgMessage, "New user_id is created. Input new user_id login and password.");
                LOG.trace("New user_id is created. Input new user_id login and password.");
//            } else {
//                request.setAttribute(AttrMessages.msgError, "User does not created. Create new user again. ");
//                LOG.error("User not created");
//            }
            return  Actions.INDEX.action;
        }
        return null;
    }
}