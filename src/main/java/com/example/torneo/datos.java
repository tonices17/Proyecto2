package com.example.torneo;

import com.example.torneo.conexion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class datos {
    public static void importarParticipantes() throws SQLException, IOException {
        BufferedReader br = null;
        BufferedReader br2 = null;
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
                    br.close();
                    br2.close(); //muy importante cerrar el fichero cuando se hayan realizado las operaciones
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static void insertParticipantes(BufferedReader br ) throws IOException, SQLException {
        String linea;
        Connection cnx = conexion.getConnexion();
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

    public static void importarClasificacion() throws SQLException, IOException {
        BufferedReader br = null;
        BufferedReader br2 = null;
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
                    br.close();
                    br2.close(); //muy importante cerrar el fichero cuando se hayan realizado las operaciones
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    public static void insertClasificacion(BufferedReader br ) throws IOException, SQLException {
        String linea;
        Connection cnx = conexion.getConnexion();
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
    public static void importarPremios() throws SQLException, IOException {
        BufferedReader br = null;
        BufferedReader br2 = null;
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
                    br.close();
                    br2.close(); //muy importante cerrar el fichero cuando se hayan realizado las operaciones
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    public static void insertPremios(BufferedReader br ) throws IOException, SQLException {
        String linea;
        Connection cnx = conexion.getConnexion();
        PreparedStatement pstm = cnx.prepareStatement("Insert into premios values (?,?,?,?,?)");
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split("\\|");
            if (Character.isDigit(linea.charAt(1))) {
                String posicion = partes[0];
                String torneo = partes[1];
                String tipo_premio = partes[2];
                String ganador = partes[3];
                String cantidad = partes[4];
                pstm.setInt(1, Integer.parseInt(posicion.substring(1, posicion.length() - 1)));
                pstm.setString(2, torneo.substring(1, torneo.length() - 1));
                pstm.setString(3, tipo_premio.substring(1, tipo_premio.length() - 1));
                if (!ganador.isEmpty()) {
                    pstm.setString(4, ganador.substring(1, ganador.length() - 1));
                } else {
                    pstm.setString(4, ganador);
                }
                pstm.setInt(5, Integer.parseInt(cantidad.substring(1, cantidad.length() - 1)));
                pstm.executeUpdate();
            }
        }
    }
}
