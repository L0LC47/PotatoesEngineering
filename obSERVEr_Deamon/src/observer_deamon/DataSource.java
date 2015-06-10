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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public void Update(String s, short vel, String pos, Timestamp time) {
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

    public String[] Query(String s, String serial) {
        String[] res = new String[2];
        ResultSet rs = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(s);
            pstmt.setString(1, serial);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());//Non riesco a eseguire la query...");
        }
        try {
            if (rs.next()) {
                res[0] = rs.getString(1); //user
                res[1] = rs.getString(2); //targa
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());//Result Set vuoto o non valido...");
        }
        return res;
    }

}
