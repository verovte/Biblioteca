/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;
import java.util.*;

/**
 *
 * @author verovte
 */
public class database {
    
          /* DATOS PARA LA CONEXION */
  /** base de datos por defecto es test*/
 private static String JDBC_DRIVER;
    private static String URL;
    private static String USER;
    private static String PASS;
    private static Driver driver = null;
    private static String JDBC_FILE_NAME = "jdbc";

    public static Properties loadProperties(String file) {
        Properties prop = new Properties();
        ResourceBundle bundle = ResourceBundle.getBundle(file);
        Enumeration e = bundle.getKeys();
        String key = null;
        while (e.hasMoreElements()) {
            key = (String) e.nextElement();
            prop.put(key, bundle.getObject(key));
        }
        JDBC_DRIVER = prop.getProperty("driver");
       URL = prop.getProperty("url");
        USER = prop.getProperty("user");
        PASS = prop.getProperty("pass");
        return prop;
    }

    public static synchronized Connection getConnection()
            throws SQLException {
        if (driver == null) {
            try {
//Cargamos las propiedades de conexiona la BD
                loadProperties(JDBC_FILE_NAME);
//Se registra el driver
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
            } catch (Exception e) {
                System.out.println("Fallo en cargar el driver JDBC");
                e.printStackTrace();
            }
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
//Cierre del PrepareStatement

    public static void close(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
//Cierre de la conexion

    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
}
