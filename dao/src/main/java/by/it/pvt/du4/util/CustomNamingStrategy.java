package by.it.pvt.du4.util;

import org.hibernate.cfg.DefaultNamingStrategy;

public class CustomNamingStrategy extends DefaultNamingStrategy {
    private static String regex = "([a-z])([A-Z]+)";
    private static String replacement = "$1_$2";

    @Override
    public String classToTableName(String className) {
        return   camelToSnake(super.classToTableName(className));
    }



    private String camelToSnake(String name){
        return name.replaceAll(regex, replacement).toUpperCase();
    }
}
