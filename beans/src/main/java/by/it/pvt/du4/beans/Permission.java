package by.it.pvt.du4.beans;

import java.io.Serializable;

public class Permission implements Serializable {
    private int id;
    private int role;
    private int command;
    private boolean permission;

    public Permission() {
    }

    public Permission(int id, int role, int command, boolean permission) {
        this.id = id;
        this.role = role;
        this.command = command;
        this.permission = permission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", role=" + role +
                ", command=" + command +
                ", permission=" + permission +
                '}';
    }
}
