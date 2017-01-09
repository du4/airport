package by.it.pvt.du4.commands;

import by.it.pvt.du4.beans.Command;
import by.it.pvt.du4.beans.Permission;
import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public enum Actions {
    SIGNUP {{ this.action = new CmdSignUp();}},
    LOGIN { {this.action = new CmdLogin();}},
    LOGOUT {{this.action = new CmdLogout();}},
    INDEX {{this.action = new CmdIndex();}},
    PROFILE {{this.action = new CmdProfile();}},
    ERROR {{this.action = new CmdError();}},
    LISTUSERS {{this.action = new CmdListUsers();}},
    STUFFINGCREW {{this.action = new CmdStuffingCrew();}},
    NEWFLIGHT {{this.action = new CmdNewFlight();}};


    protected Action action = null;

    public static Action defineFrom(HttpServletRequest request){
        Action result ;
        String command = request.getParameter("command");
        if (command != null && !command.isEmpty()) {
            try {
                command = command.toUpperCase();
                if(checkPermission(command.toLowerCase(), request)){
                    result = Actions.valueOf(command).action;
                }else {
                    result = Actions.ERROR.action;
                }

            } catch (IllegalArgumentException e) {
                result = Actions.ERROR.action;
            }
        }else {
            result = Actions.INDEX.action;
        }
        return result;
    }
    @SuppressWarnings("unchecked")
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

        if (commandID==-1)return false;

        List<Permission> permissions = (List<Permission>) session.getAttribute("permissions");
        User user = (User) session.getAttribute("user");
        if (user == null){
            user = new User("tmpUser");
        }

        if (user.getRole() == Role.ADMINISTRATOR_ROLE){
            return true;
        }

        for (Permission p:permissions) {
            if (p.getCommand()==commandID && user.getRole()==p.getRole() && p.isPermission()){
                return true;
            }
        }
        return false;
    }
}