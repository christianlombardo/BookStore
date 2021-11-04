package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {
    private static Connection connection;

    public static Connection getConnection(){
        if(connection == null){
            ResourceBundle rb = ResourceBundle.getBundle("connection");
            String URL = rb.getString("URL");
            String USERNAME = rb.getString("USERNAME");
            String PASSWORD = rb.getString("PASSWORD");
            try {
                connection = DriverManager.getConnection(URL , USERNAME, PASSWORD);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }
}
