/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer_deamon;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

/**
 *
 * @author Alberto
 */
public final class DataSource { //PATTERN: Singleton

    private Properties myProperties = new Properties();
    private Connection connection;
    private static DataSource instance;

    private DataSource() { 

        // Caricamento driver
        try {
            InputStream is = new FileInputStream("jdbc.properties");
            myProperties.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class.forName(myProperties.getProperty("dbmanager.driver"));
        } catch (ClassNotFoundException e) {
            System.out.println("DatabaseManager unable to find Driver class");
            System.exit(-1003);
        }

        // Creazione connessione
        String UrlDB = myProperties.getProperty("dbmanager.url");
        String UsernameDB = myProperties.getProperty("dbmanager.user");
        String PasswordDB = myProperties.getProperty("dbmanager.password");
        try {
            connection = DriverManager.getConnection(UrlDB, UsernameDB, PasswordDB);
        } catch (SQLException e) {
            System.out.println("Non riesco a connettermi...");
        }
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public void Query(String s, short vel, String pos, Timestamp time) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(s);
            pstmt.setInt(1, Integer.valueOf(vel));
            pstmt.setString(2, pos);
            pstmt.setTimestamp(3, time);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());//Non riesco a eseguire la query...");
        }
    }

}
