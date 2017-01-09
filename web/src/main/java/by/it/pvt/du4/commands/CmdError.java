package by.it.pvt.du4.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class CmdError extends Action {

    @Override
    public Action execute(HttpServletRequest request, HttpServletResponse response) {
        return Actions.ERROR.action;
    }
}