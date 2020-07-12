package com.cov;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    public static String getStringByKey(String filename, String key) {
        Properties prop = loadProperties(filename);
        return prop.getProperty(key);
    }

    public static Properties loadProperties(String filename) {
        Properties prop = new Properties();
        try {
            prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
