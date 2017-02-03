package by.it.pvt.du4.commands;

import by.it.pvt.du4.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class CmdUserManagement extends Action {
    private static final Logger LOG = LoggerFactory.getLogger(CmdUserManagement.class);

    @Override
    public Action execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

//        if ("POST".equalsIgnoreCase(request.getMethod())){
//            User user=new User();
//            try {
//                user.setId(Form.getInt(request,"ID"));
//                user.setLogin(Form.getString(request, "Login", Patterns.LOGIN));
//                user.setPass(Form.getString(request, "Password", Patterns.PASSWORD));
//                user.setEmail(Form.getString(request, "Email", Patterns.EMAIL));
//                user.setRole_id(Form.getInt(request,"fk_Role"));
//                request.setAttribute(AttrMessages.msgMessage,user);
//                if (user.getId() > 0){
//                    UserService.getInstance().update(user);
//                    LOG.trace("Update user_id:"+user);
//                }
//                if (user.getId() < 0){
//                    user.setId(user.getId()*(-1));
//                    UserService.getInstance().delete(user);
//                    LOG.trace("Delete user_id="+user);
//                }
//                if (user.getId() == 0){
//                    UserService.getInstance().create(user);
//                    LOG.trace("Create user_id"+user);
//                }
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//                LOG.error(""+e);
//                request.setAttribute(AttrMessages.msgMessage,"Error");
//            }
//        }
//        SessionAttrSesHelper.setRolesToAttribute(request);
//        List<User> users = DictionaryServiceUtil.getInstance().getUsers();
//        if (users == null) {
//            LOG.trace("No users found.");
//            request.setAttribute(AttrMessages.msgError,"No users found.");
//        } else {
//            request.setAttribute(AttrMessages.msgMessage,"Read usersCount=" + users.size());
//            request.setAttribute("users", users);
//            LOG.trace("Read all users, user_id count = "+users.size());
//        }
        return null;
    }
}
