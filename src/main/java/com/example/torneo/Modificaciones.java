package com.example.torneo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Modificaciones {
    @FXML
    private TextField textRanking;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textFide;
    @FXML
    private TextField textIdfide;
    @FXML
    private TextField textOrigen;
    @FXML
    private TextField textTorneo;
    @FXML
    private TextField textHotel;

    public void botonAñadir() {
        try {
            int ranking = Integer.parseInt(textRanking.getText());
            String nombre = textNombre.getText();
            int fide = Integer.parseInt(textFide.getText());
            int idfide = Integer.parseInt(textIdfide.getText());
            String origen = textOrigen.getText();
            String hotel = textHotel.getText();
            String torneo = textTorneo.getText();
            Connection cnx = HelloApplication.getConnexion();
            PreparedStatement stm = cnx.prepareStatement("INSERT INTO participantes (ranking,nombre,fide,fideid,origen,hotel,torneo) VALUES (?,?,?,?,?,?,?);");
            stm.setInt(1, ranking);
            stm.setString(2, nombre);
            stm.setInt(3, fide);
            stm.setInt(4, idfide);
            stm.setString(5, origen);
            stm.setString(6, hotel);
            stm.setString(7, torneo);
            stm.executeUpdate();
            HelloApplication.mensajeExito();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error en la aplicacion");
            alert.showAndWait();
        }
    }

    public void descalificar() throws SQLException {
        try {
            String nombre = textNombre.getText();
            Connection cnx = HelloApplication.getConnexion();
            PreparedStatement stm = cnx.prepareStatement(" SET FOREIGN_KEY_CHECKS = 0;");
            PreparedStatement stm2 = cnx.prepareStatement("DELETE FROM participantes WHERE nombre = ?;");
            PreparedStatement stm3 = cnx.prepareStatement("DELETE FROM clasificacion WHERE nombre = ?;");
            PreparedStatement stm4 = cnx.prepareStatement(" SET FOREIGN_KEY_CHECKS = 1;");
            stm2.setString(1,nombre);
            stm3.setString(1,nombre);
            stm.executeUpdate();
            stm2.executeUpdate();
            stm3.executeUpdate();
            stm4.executeUpdate();
            Datos.importarbase2();
            HelloApplication.mensajeExito();
        } catch (NullPointerException e ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error en la aplicacion");
            alert.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
