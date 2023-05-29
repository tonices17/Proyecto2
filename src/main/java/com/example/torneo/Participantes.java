package com.example.torneo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        this.FIDE = new SimpleIntegerProperty(FIDE);
        this.IDFIDE = new SimpleIntegerProperty(IDFIDE);
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

    public static ResultSet mostrarB() throws SQLException {
        Connection cnx = conexion.getConnexion();
        Statement stm = cnx.createStatement();
        ResultSet rs2 = stm.executeQuery("select * from participantes WHERE torneo='B' order by ranking");

        rs2.close();
        stm.close();

        return rs2;
    }
}
