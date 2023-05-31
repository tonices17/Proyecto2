package com.example.torneo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Datos {
    public static void importarbase() throws SQLException {
        Connection cnx = HelloApplication.getConnexion();
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery("drop database if exists torneo");
        ResultSet rs2 = stm.executeQuery("Create database torneo");
        ResultSet rs3 = stm.executeQuery("use torneo");
        ResultSet rs4 = stm.executeQuery("CREATE TABLE participantes(ranking int, nombre varchar(50), fide int(20), fideid int(20), origen varchar (50), hotel varchar(50), torneo varchar(50), PRIMARY KEY (ranking, torneo))");
        ResultSet rs5 = stm.executeQuery("CREATE TABLE clasificacion (posicion int, torneo varchar(50), ranking int, nombre varchar(50), fide int, origen varchar(50), PRIMARY KEY (posicion, torneo), FOREIGN KEY (ranking) REFERENCES participantes (ranking))");
        ResultSet rs6 = stm.executeQuery("CREATE TABLE premios (id int, posicion int, torneo varchar(50), tipo_premio varchar(50), ganador varchar(50), cantidad int, FOREIGN KEY (posicion, torneo) REFERENCES clasificacion (posicion, torneo), PRIMARY KEY (posicion, tipo_premio, torneo))");
        rs.close();
        rs2.close();
        rs3.close();
        rs4.close();
        rs5.close();
        rs6.close();
        stm.close();
    }
    public static void importarParticipantes() throws SQLException {
        BufferedReader br = null;
        BufferedReader br2;
        try {
            br = new BufferedReader(new FileReader("ParticipantesTorneoA.csv"));
            br2 = new BufferedReader(new FileReader("ParticipantesTorneoB.csv"));
            insertParticipantes(br);
            insertParticipantes(br2);
        } catch (FileNotFoundException e) { // qué hacer si no se encuentra el fichero
            e.printStackTrace();
        } catch (IOException  e) { // qué hacer si hay un error en la lectura del fichero
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close(); //muy importante cerrar el fichero cuando se hayan realizado las operaciones
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static void insertParticipantes(BufferedReader br ) throws IOException, SQLException {
        String linea;
        Connection cnx = HelloApplication.getConnexion();
        PreparedStatement pstm = cnx.prepareStatement("Insert into participantes values (?,?,?,?,?,?,?)");
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split("\\|");
            if (Character.isDigit(linea.charAt(1))) {
                String ranking = partes[0];
                String nombre = partes[2];
                String fide = partes[4];
                String fideid = partes[6];
                String origen = partes[8];
                String hotel = partes[9];
                String torneo = partes[10];
                pstm.setInt(1, Integer.parseInt(ranking.substring(1, ranking.length() - 1)));
                pstm.setString(2, nombre.substring(1, nombre.length() - 1));
                pstm.setInt(3, Integer.parseInt(fide.substring(1, fide.length() - 1)));
                pstm.setInt(4, Integer.parseInt(fideid.substring(1, fideid.length() - 1)));
                if (!origen.isEmpty()) {
                    pstm.setString(5, origen.substring(1, origen.length() - 1));
                } else {
                    pstm.setString(5, origen);
                }
                pstm.setString(6, hotel.substring(1, hotel.length() - 1));
                pstm.setString(7, torneo.substring(1, torneo.length() - 1));
                pstm.executeUpdate();
            }
        }
    }

    public static void importarClasificacion() throws SQLException {
        BufferedReader br = null;
        BufferedReader br2;
        try {
            br = new BufferedReader(new FileReader("ClasificacionTorneoA.csv"));
            br2 = new BufferedReader(new FileReader("ClasificacionTorneoB.csv"));
            insertClasificacion(br);
            insertClasificacion(br2);
        } catch (FileNotFoundException e) { // qué hacer si no se encuentra el fichero
            e.printStackTrace();
        } catch (IOException  e) { // qué hacer si hay un error en la lectura del fichero
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close(); //muy importante cerrar el fichero cuando se hayan realizado las operaciones
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    public static void insertClasificacion(BufferedReader br ) throws IOException, SQLException {
        String linea;
        Connection cnx = HelloApplication.getConnexion();
        PreparedStatement pstm = cnx.prepareStatement("Insert into clasificacion (posicion, torneo, ranking, nombre, fide, origen) values (?,?,?,?,?,?)");
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split("\\|");
            if (Character.isDigit(linea.charAt(1))) {
                String posicion = partes[0];
                String torneo = partes[1];
                String ranking = partes[2];
                String nombre = partes[5];
                String fide = partes[14];
                String origen = partes[16];
                pstm.setInt(1, Integer.parseInt(posicion.substring(1, posicion.length() - 1)));
                pstm.setString(2, torneo.substring(1, torneo.length() - 1));
                pstm.setInt(3, Integer.parseInt(ranking.substring(1, ranking.length() - 1)));
                pstm.setString(4, nombre.substring(1, nombre.length() - 1));
                pstm.setInt(5, Integer.parseInt(fide.substring(1, fide.length() - 1)));
                if (!origen.isEmpty()) {
                    pstm.setString(6, origen.substring(1, origen.length() - 1));
                } else {
                    pstm.setString(6, origen);
                }
                pstm.executeUpdate();
            }
        }
    }
    public static void importarPremios() throws SQLException {
        BufferedReader br = null;
        BufferedReader br2;
        try {
            br = new BufferedReader(new FileReader("PremiosTorneoA.csv"));
            br2 = new BufferedReader(new FileReader("PremiosTorneoB.csv"));
            insertPremios(br);
            insertPremios(br2);
        } catch (FileNotFoundException e) { // qué hacer si no se encuentra el fichero
            e.printStackTrace();
        } catch (IOException  e) { // qué hacer si hay un error en la lectura del fichero
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close(); //muy importante cerrar el fichero cuando se hayan realizado las operaciones
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    public static void insertPremios(BufferedReader br ) throws IOException, SQLException {
        String linea;
        Connection cnx = HelloApplication.getConnexion();
        PreparedStatement pstm = cnx.prepareStatement("Insert into premios (id, posicion, torneo, tipo_premio, ganador, cantidad) values (?,?,?,?,?,?)");
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split("\\|");
            if (Character.isDigit(linea.charAt(1))) {
                String id = partes[0];
                String posicion = partes[1];
                String torneo = partes[2];
                String tipo_premio = partes[3];
                String ganador = partes[4];
                String cantidad = partes[5];
                pstm.setInt(1, Integer.parseInt(id.substring(1, id.length() - 1)));
                pstm.setInt(2, Integer.parseInt(posicion.substring(1, posicion.length() - 1)));
                pstm.setString(3, torneo.substring(1, torneo.length() - 1));
                pstm.setString(4, tipo_premio.substring(1, tipo_premio.length() - 1));
                if (!ganador.isEmpty()) {
                    pstm.setString(5, ganador.substring(1, ganador.length() - 1));
                } else {
                    pstm.setString(5, ganador);
                }
                pstm.setInt(6, Integer.parseInt(cantidad.substring(1, cantidad.length() - 1)));
                pstm.executeUpdate();
            }
        }
    }

    public static void importarbase2() throws SQLException {
        Connection cnx = HelloApplication.getConnexion();
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery("drop table if exists optaA");
        ResultSet rs2 = stm.executeQuery("create table optaA as select c.posicion, pa.nombre, pa.fide, pa.origen, pa.hotel from participantes pa left join clasificacion c on pa.nombre = c.nombre where pa.torneo = 'A' order by c.posicion");
        ResultSet rs3 = stm.executeQuery("drop table if exists optaB");
        ResultSet rs4 = stm.executeQuery("create table optaB as select c.posicion, pa.nombre, pa.fide, pa.origen, pa.hotel from participantes pa left join clasificacion c on pa.nombre = c.nombre where pa.torneo = 'B' order by c.posicion");
        ResultSet rs5 = stm.executeQuery("drop table if exists premiosfinales");
        ResultSet rs6 = stm.executeQuery("CREATE TABLE premiosfinales (id int, nombre varchar(50), tipo_premio varchar(50), cantidad int)");
        rs.close();
        rs2.close();
        rs3.close();
        rs4.close();
        rs5.close();
        rs6.close();
        stm.close();
    }
}