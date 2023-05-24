package com.example.torneo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControlParticipantesA implements Initializable {

    @FXML
    private TableView<Participantes> tablaA;
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
    private void insertarFilas() throws SQLException, IOException {
        if(cont == 1){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ErrorImport.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("ERROR");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
        while(cont == 0) {
            ResultSet rs = Participantes.mostrarA();
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
}
