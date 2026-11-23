/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DBUtils {

    private static final String DB_NAME = "AssignmentMobie";
    private static final String DB_USER_NAME = "sa";
    private static final String DB_PASWD = "sa";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=" + DB_NAME;
        conn = DriverManager.getConnection(url, DB_USER_NAME, DB_PASWD);
        return conn;
    }

    public static void main(String[] args) {
        try {
            Connection c = getConnection();
            System.out.println(c);
        } catch (Exception e) {
            System.out.println("Fail SQL");
        }
    }
    
}
