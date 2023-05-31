package com.example.torneo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControlClasi implements Initializable {

    @FXML
    private TableView<Clasificado> tablaA;
    @FXML
    private TableView<Clasificado> tablaB;
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
    private void insertarFilasA() throws SQLException {
        if(cont == 1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error en la aplicacion");
            alert.showAndWait();
        }
        while(cont == 0) {
            ResultSet rs = Clasificado.clasificacionA();
            variables(rs, tablaA);
        }
    }

    private void variables(ResultSet rs, TableView<Clasificado> tablaA) throws SQLException {
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
            ResultSet rs = Clasificado.clasificacionB();
            variables(rs, tablaB);
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
