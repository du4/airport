package by.it.pvt.du4;

import by.it.pvt.du4.beans.Command;
import by.it.pvt.du4.beans.Permission;
import by.it.pvt.du4.beans.Role;
import by.it.pvt.du4.beans.User;
import by.it.pvt.du4.exceptions.ServiceException;
import by.it.pvt.du4.util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PermissionService {
    private static volatile PermissionService instance;
    private static final Logger LOG = LoggerFactory.getLogger(PermissionService.class);

    private PermissionService() {
    }

    public static PermissionService getInstance() {
        if (instance == null) {
            synchronized (PermissionService.class){
                if (instance == null){
                    instance = new PermissionService();
                }
            }
        }
        return instance;
    }

    public boolean checkPermission(User user, String cmd) throws ServiceException {
        List<Command> commands = DictionaryUtil.getInstance().getCommands();
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

        List<Permission> permissions = DictionaryUtil.getInstance().getPermissions() ;//(List<Permission>) session.getAttribute("permissions");
        if (user == null){
            user = new User("tmpUser");
        }
        if (user.getRole().equals(Role.ADMINISTRATOR_ROLE)){
            return true;
        }
        for (Permission p:permissions) {
            if (p.getCommand_id().equals(commandID) && user.getRole().equals(p.getRole_id()) && p.getPermission()){
                return true;
            }
        }
        return false;
    }

}
