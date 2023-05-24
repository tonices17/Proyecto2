package com.example.torneo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDatos {
    public static void importarbase() throws SQLException {
        Connection cnx = conexion.getConnexion();
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery("drop database if exists torneo");
        ResultSet rs2 = stm.executeQuery("Create database torneo");
        ResultSet rs3 = stm.executeQuery("use torneo");
        ResultSet rs4 = stm.executeQuery("CREATE TABLE participantes(ranking int, nombre varchar(50), fide int(20), fideid int(20), origen varchar (50), hotel varchar(50), torneo varchar(50), PRIMARY KEY (ranking, torneo))");
        ResultSet rs5 = stm.executeQuery("CREATE TABLE clasificacion (posicion int, torneo varchar(50), ranking int, nombre varchar(50), fide int, origen varchar(50), PRIMARY KEY (posicion, torneo), FOREIGN KEY (ranking) REFERENCES participantes (ranking))");
        ResultSet rs6 = stm.executeQuery("CREATE TABLE premios (posicion int, torneo varchar(50), tipo_premio varchar(50), ganador varchar(50), cantidad int, FOREIGN KEY (posicion, torneo) REFERENCES clasificacion (posicion, torneo), PRIMARY KEY (posicion, tipo_premio, torneo))");
        rs.close();
        rs2.close();
        rs3.close();
        rs4.close();
        rs5.close();
        rs6.close();
        stm.close();
    }
}
