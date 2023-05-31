package com.example.torneo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Premio {
    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty posicion;
    private final SimpleStringProperty torneo;
    private final SimpleStringProperty tipo_premio;
    private final SimpleStringProperty ganador;
    private final SimpleIntegerProperty cantidad;

    public Premio(int id, int posicion, String torneo, String tipo_premio, String ganador, int cantidad) {
        this.id = new SimpleIntegerProperty(id);
        this.posicion = new SimpleIntegerProperty(posicion);
        this.torneo =  new SimpleStringProperty(torneo);
        this.tipo_premio =  new SimpleStringProperty(tipo_premio);
        this.ganador =  new SimpleStringProperty(ganador);
        this.cantidad = new SimpleIntegerProperty(cantidad);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
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

    public String getTipo_premio() {
        return tipo_premio.get();
    }

    public SimpleStringProperty tipo_premioProperty() {
        return tipo_premio;
    }

    public void setTipo_premio(String tipo_premio) {
        this.tipo_premio.set(tipo_premio);
    }

    public String getGanador() {
        return ganador.get();
    }

    public SimpleStringProperty ganadorProperty() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador.set(ganador);
    }

    public int getCantidad() {
        return cantidad.get();
    }

    public SimpleIntegerProperty cantidadProperty() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }

}
