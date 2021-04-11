package com.db;

import com.DTOs.UserDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbHelper {

    public static Connection con;

    public DbHelper() {
       /* ResultSet rs = null;
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "iwish", "iwish");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }*/
    }

    public static Connection getDbCon() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "iwish", "iwish");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "||" +ex.getSQLState());
        }
        return con;
    }

}
