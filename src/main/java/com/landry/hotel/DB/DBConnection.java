package com.landry.hotel.DB;

import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
    public Connection getConnection(String databaseName, String databaseUser, String databasePassword){
        Connection conn = null;
        String url ="jdbc:mysql://localhost:3306/"+databaseName;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(url,databaseUser,databasePassword);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }
}
