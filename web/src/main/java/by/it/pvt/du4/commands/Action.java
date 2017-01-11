package by.it.pvt.du4.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract public class Action {
    public abstract Action execute(HttpServletRequest request, HttpServletResponse response);

    public Action getRedirectAction() {
        return redirectAction;
    }

    public void setRedirectAction(Action redirectAction) {
        this.redirectAction = redirectAction;
    }

    private Action redirectAction;

    @Override
    public String toString() {
        String name=this.getClass().getSimpleName();
        name=name.replace("Cmd","");
        return name;
    }

    public String getJsp(){
        String name="/" + this.toString().toLowerCase()+".jsp";
        if (!name.equalsIgnoreCase("/index.jsp")){
            name = "/WEB-INF/views" + name;
        }
        return   name;
    }
}
