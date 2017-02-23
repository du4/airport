package by.it.pvt.du4.controller.commands;

import by.it.pvt.du4.beans.Command;
import by.it.pvt.du4.beans.Permission;
import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.service.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Pattern Action
 */
public enum Actions {
    SIGNUP {{ this.action = new CmdSignUp();}},
    LOGIN { {this.action = new CmdLogin();}},
    LOGOUT {{this.action = new CmdLogout();}},
    INDEX {{this.action = new CmdIndex();}},
    PROFILE {{this.action = new CmdProfile();}},
    ERROR {{this.action = new CmdError();}},
    USERMANAGEMENT {{this.action = new CmdUserManagement();}},
    STUFFINGCREW {{this.action = new CmdStuffingCrew();}},
    NEWFLIGHT {{this.action = new CmdNewFlight();}};


    protected Action action = null;

    private static final Logger LOG = LoggerFactory.getLogger(Actions.class);

    /**
     * Extract Action from_id HttpServletRequest. If action deny by permissions
     * or action daes not exist -> return Error page
     * @param request
     * @return Action
     */
    public static Action defineFrom(HttpServletRequest request) throws ServiceException {
        Action result;
        String command = request.getParameter("action");
        if (command != null && !command.isEmpty()) {
            try {
                command = command.toUpperCase();
                if(checkPermission(command.toLowerCase(), request)){
                    result = Actions.valueOf(command).action;
                }else {
                    throw new IllegalArgumentException("Error 403 - Forbidden");
                }

            } catch (IllegalArgumentException e) {
                result = Actions.ERROR.action;
                request.setAttribute(AttrMessages.msgError, e.getMessage());
                LOG.error(""+e);
            }
        }else {
            LOG.trace("action="+command);
            result = Actions.INDEX.action;
        }
        return result;
    }

    public static Action getErrorAction(){
        return Actions.ERROR.action;
    }

    /**
     * Check if action(cmd) are allowed to_id current user_id(from_id session).
     * If allowed return - true, else return false
     * @param cmd request
     * @return boolean true if command is permitted, false if disabled
     */
    private static boolean checkPermission(String cmd, HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        List <Command> commands = null;// DictionaryServiceUtil.getInstance().getCommands();
        List<Role>roles = null; //DictionaryServiceUtil.getInstance().getRoles();
        Long commandID = -1L;
        for (Command command : commands) {
            if (command.getName().equalsIgnoreCase(cmd)) {
                commandID = command.getId();
                break;
            }
        }

        if (commandID==-1){
            throw new IllegalArgumentException("Error 404 - Not Found");
        }

        List<Permission> permissions = null;//DictionaryServiceUtil.getInstance().getPermissions();
        User user = (User) session.getAttribute("user");
        if (user == null){
            user = new User("tmpUser", Role.USER_ROLE);
        }
        if (user.getRole().getId().equals(Role.ADMINISTRATOR_ROLE)){
            return true;
        }
        for (Permission p:permissions) {
            if (p.getCommand_id().getId().equals(commandID) && user.getRole().getId().equals(p.getRole_id().getId()) && p.getPermission()){
                return true;
            }
        }
        return false;
    }
}