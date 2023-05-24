package com.example.torneo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    public static Connection getConnexion() throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/torneo?allowMultiQueries=true";
        String user = "root";
        String password = "root";
        return DriverManager.getConnection(url, user, password);
    }
}
