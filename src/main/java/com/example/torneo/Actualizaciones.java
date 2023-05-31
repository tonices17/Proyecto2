package com.example.torneo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Actualizaciones implements Initializable {
    @FXML
    private TextField textNombre;
    @FXML
    private ChoiceBox<String> checkBox;
    @FXML
    private TextField textValor;
    private final String[] seleccion = {"Nombre","Origen","Hotel"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkBox.getItems().addAll(seleccion);
    }
    public void actualizarParticipante() throws SQLException, IOException {
        String nombre = textNombre.getText();
        Connection cnx = HelloApplication.getConnexion();
        try {
            if (checkBox.getValue().equals("Nombre")) {
                String valor = textValor.getText();
                PreparedStatement pstm = cnx.prepareStatement("UPDATE participantes SET nombre = ? WHERE nombre = ?;");
                pstm.setString(1, valor);
                pstm.setString(2, nombre);
                pstm.executeUpdate();
            } else if (checkBox.getValue().equals("Origen")) {
                String valor = textValor.getText();
                PreparedStatement pstm = cnx.prepareStatement("UPDATE participantes SET origen = ? WHERE nombre = ?;");
                pstm.setString(1, valor);
                pstm.setString(2, nombre);
                pstm.executeUpdate();
            } else if (checkBox.getValue().equals("Hotel")) {
                String valor = textValor.getText();
                PreparedStatement pstm = cnx.prepareStatement("UPDATE participantes SET hotel = ? WHERE nombre = ?;");
                pstm.setString(1, valor);
                pstm.setString(2, nombre);
                pstm.executeUpdate();
            }
            HelloApplication.mensajeExito();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error en la aplicacion");
            alert.showAndWait();
        }
    }
}
