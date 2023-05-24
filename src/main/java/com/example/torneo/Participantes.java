package com.example.torneo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.*;

public class Participantes {
    private final SimpleIntegerProperty ranking;
    private final SimpleStringProperty nombre;
    private final SimpleIntegerProperty FIDE;
    private final SimpleIntegerProperty IDFIDE;
    private final SimpleStringProperty origen;
    private final SimpleStringProperty hotel;
    private final SimpleStringProperty torneo;

    public Participantes(int ranking, String nombre, int FIDE, int IDFIDE, String origen, String hotel, String torneo) {
        this.ranking = new SimpleIntegerProperty(ranking);
        this.nombre = new SimpleStringProperty(nombre);
        this.FIDE = new SimpleIntegerProperty(FIDE);;
        this.IDFIDE = new SimpleIntegerProperty(IDFIDE);;
        this.origen = new SimpleStringProperty(origen);
        this.hotel = new SimpleStringProperty(hotel);
        this.torneo = new SimpleStringProperty(torneo);
    }

    public int getRanking() {
        return ranking.get();
    }

    public SimpleIntegerProperty rankingProperty() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking.set(ranking);
    }

    public String getNombre() {
        return nombre.get();
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public int getFIDE() {
        return FIDE.get();
    }

    public SimpleIntegerProperty FIDEProperty() {
        return FIDE;
    }

    public void setFIDE(int FIDE) {
        this.FIDE.set(FIDE);
    }

    public int getIDFIDE() {
        return IDFIDE.get();
    }

    public SimpleIntegerProperty IDFIDEProperty() {
        return IDFIDE;
    }

    public void setIDFIDE(int IDFIDE) {
        this.IDFIDE.set(IDFIDE);
    }

    public String getOrigen() {
        return origen.get();
    }

    public SimpleStringProperty origenProperty() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen.set(origen);
    }

    public String getHotel() {
        return hotel.get();
    }

    public SimpleStringProperty hotelProperty() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel.set(hotel);
    }

    public String getTorneo() {
        return torneo.get();
    }

    public SimpleStringProperty torneoProperty() {
        return torneo;
    }

    public void setTorneo(String torneo) {
        this.torneo.set(torneo);
    }

    public static ResultSet mostrarA() throws SQLException {
        Connection cnx = conexion.getConnexion();
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery("select * from participantes WHERE torneo='A' order by ranking");

        rs.close();
        stm.close();

        return rs;
    }

    public static void mostrarB() throws SQLException {
        Connection cnx = conexion.getConnexion();
        Statement stm = cnx.createStatement();
        ResultSet rs2 = stm.executeQuery("select * from participantes WHERE torneo='B' order by ranking");

        while (rs2.next()) {
            int ranking = rs2.getInt(1);
            String nombre = rs2.getString(2);
            int fide = rs2.getInt(3);
            int idfide = rs2.getInt(4);
            String origen = rs2.getString(5);
            String hotel = rs2.getString(6);
            String torneo = rs2.getString(7);
            System.out.println(ranking + " " + nombre + " " + fide + " " + idfide + " " + origen + " " + hotel + " " + torneo);
        }
        rs2.close();
        stm.close();
    }
}
