package by.it.pvt.du4.util;

import org.hibernate.cfg.DefaultNamingStrategy;

public class CustomNamingStrategy extends DefaultNamingStrategy {
    @Override
    public String classToTableName(String className) {
        return super.classToTableName(className).toLowerCase();
    }
    
}
