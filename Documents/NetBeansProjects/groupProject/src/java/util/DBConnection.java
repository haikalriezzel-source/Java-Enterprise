/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author haikalriez
 */
public class DBConnection {
    private static final String URL = "jdbc:derby://localhost:1527/SportsDB";
    private static final String USER = "sukan";
    private static final String PASS = "sukan";
    
    public static Connection getConnection() throws SQLException{
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
