package com.helper;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by aaduevsky on 23.04.17.
 */

public class UIProperty {

    public static Properties property;
    private static final Logger log = LogManager.getLogger(UIProperty.class);

    public static void loadProperty(String propFile) {
        try {
            property = new Properties();
            FileInputStream fis;
            URL url = UIProperty.class.getClassLoader().getResource(propFile);
            assert url != null;
            fis = new FileInputStream(url.getPath());
            property.load(fis);
            log.debug(String.format("property file %sloaded", url.getPath()));

        } catch (IOException e) {
            System.err.println("Error: property file doesn't exist!");
        }
    }

    private static String getProp(String propName, String value) {
        log.debug(String.format("get property as json: %s %s", propName, value));
        JsonParser parser = new JsonParser();
        JsonObject myObject = parser.parse(property.getProperty(propName.toLowerCase())).getAsJsonObject();
        return myObject.get(value).getAsString();
    }

    public static JSONObject getJSONProp(String propName) {
        return new JSONObject(property.getProperty((propName.toLowerCase())));
    }

    public static String getPasswd(String propName) {
        return getProp(propName, "password");
    }

    public static String getUser(String propName) {
        return getProp(propName, "login");
    }

    public static String getProperty(String propName) {
        return property.getProperty(propName);
    }


}
