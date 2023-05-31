package com.example.torneo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControlParticipantes implements Initializable {

    @FXML
    private TableView<Participantes> tablaA;
    @FXML
    private TableView<Participantes> tablaB;
    @FXML
    private TableColumn<Participantes, Integer> columRanking;
    @FXML
    private TableColumn<Participantes, String> columNombre;
    @FXML
    private TableColumn<Participantes, Integer> columFide;
    @FXML
    private TableColumn<Participantes, Integer> columFideID;
    @FXML
    private TableColumn<Participantes, String> columOrigen;
    @FXML
    private TableColumn<Participantes, String> columHotel;
    @FXML
    private TableColumn<Participantes, String> columTorneo;
    private final ObservableList<Participantes> data = FXCollections.observableArrayList();
    private int cont;
    @FXML
    private void insertarFilasA() throws SQLException {
        if(cont == 1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error en la aplicacion");
            alert.showAndWait();
        }
        while(cont == 0) {
            ResultSet rs = Participantes.mostrarA();
            variables(rs, tablaA);
        }
    }

    private void variables(ResultSet rs, TableView<Participantes> tablaA) throws SQLException {
        while (rs.next()) {
            int ranking = rs.getInt(1);
            String nombre = rs.getString(2);
            int fide = rs.getInt(3);
            int idfide = rs.getInt(4);
            String origen = rs.getString(5);
            String hotel = rs.getString(6);
            String torneo = rs.getString(7);
            Participantes participante = new Participantes(ranking, nombre, fide, idfide, origen, hotel, torneo);
            data.add(participante);
        }
        tablaA.setItems(data);
        cont++;
    }

    @FXML
    private void insertarFilasB() throws SQLException {
        if(cont == 1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error en la aplicacion");
            alert.showAndWait();
        }
        while(cont == 0) {
            ResultSet rs = Participantes.mostrarB();
            variables(rs, tablaB);
        }
    }

    public void añadirParticipante() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MenuAñadir.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Adición de participantes");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columRanking.setCellValueFactory(new PropertyValueFactory<>("ranking"));
        columNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columFide.setCellValueFactory(new PropertyValueFactory<>("FIDE"));
        columFideID.setCellValueFactory(new PropertyValueFactory<>("IDFIDE"));
        columOrigen.setCellValueFactory(new PropertyValueFactory<>("origen"));
        columHotel.setCellValueFactory(new PropertyValueFactory<>("Hotel"));
        columTorneo.setCellValueFactory(new PropertyValueFactory<>("torneo"));
    }

    public void actualizarParticipante() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MenuActualizar.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Aztualización de participantes");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
