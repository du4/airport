package by.it.pvt.du4.controller.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class Form {
    private static final Logger LOG = LoggerFactory.getLogger(Form.class);

    static String getString(HttpServletRequest req,
                            String parameter,
                            String pattern) throws ParseException {
        String value=req.getParameter(parameter);
        if (value!=null ) {
            if (value.matches(pattern)) {
                return value;
            }else {
                throw new ParseException("Incorrect String: "+parameter,0);
            }
        }else {
            throw new NullPointerException("Empty HttpRequest parameter " + parameter);
        }
    }

    static int getInt(HttpServletRequest req, String parameter) throws ParseException {
        String value=req.getParameter(parameter);
        if (value != null) {
            if (value.matches("[0-9-]+"))
            {return (Integer.parseInt(value));}
        }
        throw new ParseException("Incorrect String: "+parameter,0);
    }

    static Long getLong(HttpServletRequest req, String parameter) throws ParseException {
        String value=req.getParameter(parameter);
        if (value != null) {
            if (value.matches("[0-9-]+"))
            {return (Long.parseLong(value));}
        }
        throw new ParseException("Incorrect String: "+parameter,0);
    }
}