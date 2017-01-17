package by.it.pvt.du4.commands;

import by.it.pvt.du4.DictionaryUtil;
import by.it.pvt.du4.UserService;
import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

class CmdUserManagement extends Action {
    private static final Logger LOG = LoggerFactory.getLogger(CmdUserManagement.class);

    @Override
    public Action execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        if (request.getMethod().equalsIgnoreCase("POST")){
            User user=new User();
            try {
                user.setId(Form.getInt(request,"ID"));
                user.setLogin(Form.getString(request, "Login", Patterns.LOGIN));
                user.setPass(Form.getString(request, "Password", Patterns.PASSWORD));
                user.setEmail(Form.getString(request, "Email", Patterns.EMAIL));
                user.setRole(Form.getInt(request,"fk_Role"));
                request.setAttribute(AttrMessages.msgMessage,user);
                if (user.getId() > 0){
                    UserService.getInstance().update(user);
                    LOG.trace("Update user:"+user);
                }
                if (user.getId() < 0){
                    user.setId(user.getId()*(-1));
                    UserService.getInstance().delete(user);
                    LOG.trace("Delete user="+user);
                }
                if (user.getId() == 0){
                    UserService.getInstance().create(user);
                    LOG.trace("Create user"+user);
                }

            } catch (ParseException e) {
                e.printStackTrace();
                LOG.error(""+e);
                request.setAttribute(AttrMessages.msgMessage,"Error");
            }
        }
        SessionAttrSesHelper.setRolesToAttribute(request);
        List<User> users = DictionaryUtil.getInstance().getUsers();
        if (users == null) {
            LOG.trace("No users found.");
            request.setAttribute(AttrMessages.msgError,"No users found.");
        } else {
            request.setAttribute(AttrMessages.msgMessage,"Read usersCount=" + users.size());
            request.setAttribute("users", users);
            LOG.trace("Read all users, user count = "+users.size());
        }
        return null;
    }
}
