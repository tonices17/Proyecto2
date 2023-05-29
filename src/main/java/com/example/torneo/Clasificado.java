package com.example.torneo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Clasificado {
    private final SimpleIntegerProperty posicion;
    private final SimpleStringProperty torneo;
    private final SimpleIntegerProperty ranking;
    private final SimpleStringProperty nombre;
    private final SimpleIntegerProperty fide;
    private final SimpleStringProperty origen;

    public Clasificado(int posicion, String torneo, int ranking, String nombre, int fide, String origen) {
        this.posicion = new SimpleIntegerProperty(posicion);
        this.torneo = new SimpleStringProperty(torneo);
        this.ranking = new SimpleIntegerProperty(ranking);
        this.nombre = new SimpleStringProperty(nombre);
        this.fide = new SimpleIntegerProperty(fide);
        this.origen = new SimpleStringProperty(origen);
    }

    public int getPosicion() {
        return posicion.get();
    }

    public SimpleIntegerProperty posicionProperty() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion.set(posicion);
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

    public int getFide() {
        return fide.get();
    }

    public SimpleIntegerProperty fideProperty() {
        return fide;
    }

    public void setFide(int fide) {
        this.fide.set(fide);
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

    public static ResultSet clasificacionA() throws SQLException {
        Connection cnx = conexion.getConnexion();
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery("select * from clasificacion WHERE torneo='A' order by posicion");

        rs.close();
        stm.close();

        return rs;
    }

    public static ResultSet clasificacionB() throws SQLException {
        Connection cnx = conexion.getConnexion();
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery("select * from clasificacion WHERE torneo='B' order by posicion");

        rs.close();
        stm.close();

        return rs;
    }
}
