package by.it.pvt.du4.controller.commands;

import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.service.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

class CmdUserManagement extends Action {
    private static final Logger LOG = LoggerFactory.getLogger(CmdUserManagement.class);
    private static SimpleDateFormat formatterDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    @Override
    public Action execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        if ("POST".equalsIgnoreCase(request.getMethod())){
            User user=new User();
            try {
                user.setId(Form.getLong(request,"ID"));
                user.setLogin(Form.getString(request, "Login", Patterns.LOGIN));
                user.setEmail(Form.getString(request, "Email", Patterns.EMAIL));
                user.setCreatedDate(formatterDateTime.parse(Form.getString(request, "CreatedDate", Patterns.PASSWORD)));
                request.setAttribute(AttrMessages.msgMessage,user);
                if (user.getId() > 0){
                    List<Role> roles  = (List<Role>) request.getSession().getAttribute("roles");
                    for(Role r : roles){
                        if (r.getId().equals(Form.getLong(request, "userRole"))) {
                            user.setRole(r);
//                            UserService.getInstance().saveOrUpdate(user);
                            break;
                        }
                    }

                    LOG.trace("Update user:"+user);
                }
                if (user.getId() < 0){
                    user.setId(user.getId()*(-1));
//                    UserService.getInstance().delete(user);
                    LOG.trace("Delete user="+user);
                }

            } catch (ParseException e) {
                e.printStackTrace();
                LOG.error(""+e);
                request.setAttribute(AttrMessages.msgMessage,"Error");
            }
        }
//        request.setAttribute("roles", DictionaryServiceUtil.getInstance().getRoles());
//        List<User> users = UserService.getInstance().getAll();
//        if (users == null) {
//            LOG.trace("No users found.");
//            request.setAttribute(AttrMessages.msgError,"No users found.");
//        } else {
//            request.setAttribute(AttrMessages.msgMessage,"Read usersCount=" + users.size());
//            request.setAttribute("users", users);
//            LOG.trace("Read all users, user count = " + users.size());
//        }
        return null;
    }
}
