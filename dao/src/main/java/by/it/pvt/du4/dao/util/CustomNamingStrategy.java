package by.it.pvt.du4.dao.util;

import org.hibernate.cfg.DefaultNamingStrategy;

class CustomNamingStrategy extends DefaultNamingStrategy {
    private static String regex = "([a-z])([A-Z]+)";
    private static String replacement = "$1_$2";

    @Override
    public String classToTableName(String className) {
        return   camelToSnake(super.classToTableName(className));
    }

    @Override
    public String propertyToColumnName(String propertyName) {
        return super.propertyToColumnName(propertyName).toUpperCase();
    }

    private String camelToSnake(String name){
        return name.replaceAll(regex, replacement).toUpperCase();
    }
}
