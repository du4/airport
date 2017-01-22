package by.it.pvt.du4.commands;

import by.it.pvt.du4.beans.Command;
import by.it.pvt.du4.beans.Permission;
import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Pattern Command
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
     * Extract Action from_id HttpServletRequest. If command deny by permissions
     * or command daes not exist -> return Error page
     * @param request
     * @return Action
     */
    public static Action defineFrom(HttpServletRequest request){
        Action result;
        String command = request.getParameter("command");
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
            LOG.trace("command="+command);
            result = Actions.INDEX.action;
        }
        return result;
    }

    /**
     * Simple return error Action
     * @return
     */
    public static Action getErrorAction(){
        return Actions.ERROR.action;
    }

    /**
     * Check if command(cmd) are allowed to_id current user_id(from_id session).
     * If allowed return - true, else return false
     * @param cmd request
     * @return boolean
     */
    private static boolean checkPermission(String cmd, HttpServletRequest request){
        HttpSession session = request.getSession();
        List <Command> commands = (List<Command>) session.getAttribute("commands");
        int commandID=-1;
        for (int i = 0 ; i < commands.size(); i++) {
            if (commands.get(i).getName().equalsIgnoreCase(cmd)){
                commandID=i+1;
                break;
            }
        }

        if (commandID==-1){
            throw new IllegalArgumentException("Error 404 - Not Found");
        }

        List<Permission> permissions = (List<Permission>) session.getAttribute("permissions");
        User user = (User) session.getAttribute("user");
        if (user == null){
            user = new User("tmpUser");
        }
        if (user.getRole_id() == Role.ADMINISTRATOR_ROLE){
            return true;
        }
        for (Permission p:permissions) {
            if (p.getCommand()==commandID && user.getRole_id()==p.getRole() && p.getPermission()){
                return true;
            }
        }
        return false;
    }
}