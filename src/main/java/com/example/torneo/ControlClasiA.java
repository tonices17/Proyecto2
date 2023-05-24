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

public class ControlClasiA implements Initializable {

    @FXML
    private TableView<Clasificado> tablaA;
    @FXML
    private TableColumn<Clasificado, Integer> columPosicion;
    @FXML
    private TableColumn<Clasificado, String> columTorneo;
    @FXML
    private TableColumn<Clasificado, Integer> columRanking;
    @FXML
    private TableColumn<Clasificado, String> columNombre;
    @FXML
    private TableColumn<Clasificado, Integer> columFide;
    @FXML
    private TableColumn<Clasificado, String> columOrigen;
    private final ObservableList<Clasificado> data = FXCollections.observableArrayList();
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
            ResultSet rs = Clasificado.clasificacionA();
            while (rs.next()) {
                int posicion = rs.getInt(1);
                String torneo = rs.getString(2);
                int ranking = rs.getInt(3);
                String nombre = rs.getString(4);
                int fide = rs.getInt(5);
                String origen = rs.getString(6);
                Clasificado clasificado = new Clasificado(posicion, torneo, ranking, nombre, fide, origen);
                data.add(clasificado);
            }
            tablaA.setItems(data);
            cont++;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columPosicion.setCellValueFactory(new PropertyValueFactory<>("posicion"));
        columTorneo.setCellValueFactory(new PropertyValueFactory<>("torneo"));
        columRanking.setCellValueFactory(new PropertyValueFactory<>("ranking"));
        columNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columFide.setCellValueFactory(new PropertyValueFactory<>("fide"));
        columOrigen.setCellValueFactory(new PropertyValueFactory<>("origen"));
    }
}
