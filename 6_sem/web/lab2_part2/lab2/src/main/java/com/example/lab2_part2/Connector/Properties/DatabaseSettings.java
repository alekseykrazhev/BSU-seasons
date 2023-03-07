package com.example.lab2_part2.Connector.Properties;

import java.util.ResourceBundle;

/**
 * Database properties class
 */
public class DatabaseSettings {
    /**
     * Resource bundle
     */
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("database");

    /**
     * Database URL
     */
    public static String URL = resourceBundle.getString("url");
    /**
     * Database username
     */
    public static String USERNAME = resourceBundle.getString("user");
    /**
     * Database driver
     */
    public static String DRIVER = resourceBundle.getString("driver");
    /**
     * Database password
     */
    public static String PASSWORD = resourceBundle.getString("password");
}
