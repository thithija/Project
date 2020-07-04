package com.example.mysqlinsert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon {

    private static final String DB_URL="jdbc:mysql://192.168.8.100/andro";
    private static final String USER = "zzz";
    private static final String PASS = "zzz";
    Connection conn;
    public Connection connectTODB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
