package com.example.torneo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControlPremios implements Initializable {

    @FXML
    private TableView<Premio> tablaA;
    @FXML
    private TableView<Premio> tablaB;
    @FXML
    private TableColumn<Premio, Integer> columID;
    @FXML
    private TableColumn<Premio, Integer> columPosicion;
    @FXML
    private TableColumn<Premio, String> columTorneo;
    @FXML
    private TableColumn<Premio, String> columTipoPremio;
    @FXML
    private TableColumn<Premio, String> columGanador;
    @FXML
    private TableColumn<Premio, Integer> columCantidad;
    private final ObservableList<Premio> data = FXCollections.observableArrayList();
    private int cont;
    @FXML
    private TextField textArea;

    @FXML
    private void insertarPremiosA() throws SQLException, IOException {
        tablaA.getItems().clear();
        Premios.insertarpremiosA();
        if (cont == 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error en la aplicacion");
            alert.showAndWait();
        }
        while (cont == 0) {
            ResultSet rs = Premios.mostrarPremiosA();
            while (rs.next()) {
                variables(rs);
            }
            tablaA.setItems(data);
            cont++;
        }
    }

    @FXML
    private void insertarPremiosB() throws SQLException, IOException {
        tablaB.getItems().clear();
        Premios.insertarpremiosB();
        if (cont == 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error en la aplicacion");
            alert.showAndWait();
        }
        while (cont == 0) {
            ResultSet rs = Premios.mostrarPremiosB();
            while (rs.next()) {
                variables(rs);
            }
            tablaB.setItems(data);
            cont++;
        }
    }

    @FXML
    private void buscarPersonaA() throws SQLException {
        tablaA.getItems().clear();
        String ganadorBusqueda = "%"+textArea.getText()+"%";
        Connection cnx = HelloApplication.getConnexion();
        Statement stm = cnx.createStatement();
        String cadena = "SELECT * FROM premios WHERE ganador LIKE '"+ganadorBusqueda+"';";
        ResultSet rs2 = stm.executeQuery(cadena);
        while(rs2.next()){
        variables(rs2);
        }
        tablaA.setItems(data);
        cont++;
    }

    @FXML
    private void buscarPersonaB() throws SQLException {
        tablaB.getItems().clear();
        String ganadorBusqueda = "%"+textArea.getText()+"%";
        Connection cnx = HelloApplication.getConnexion();
        Statement stm = cnx.createStatement();
        String cadena = "SELECT * FROM premios WHERE ganador LIKE '"+ganadorBusqueda+"';";
        ResultSet rs2 = stm.executeQuery(cadena);
        while(rs2.next()){
            variables(rs2);
        }
        tablaB.setItems(data);
        cont++;
    }

    private void variables(ResultSet rs2) throws SQLException {
            int id = rs2.getInt("id");
            int posicion = rs2.getInt(2);
            String torneo = rs2.getString(3);
            String tipo_premio = rs2.getString(4);
            String ganador = rs2.getString(5);
            int cantidad = rs2.getInt(6);
            Premio premio = new Premio(id, posicion, torneo, tipo_premio, ganador, cantidad);
            data.add(premio);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columPosicion.setCellValueFactory(new PropertyValueFactory<>("posicion"));
        columTorneo.setCellValueFactory(new PropertyValueFactory<>("torneo"));
        columTipoPremio.setCellValueFactory(new PropertyValueFactory<>("tipo_premio"));
        columGanador.setCellValueFactory(new PropertyValueFactory<>("ganador"));
        columCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
    }

    public void exportarA() throws SQLException, IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("PremiosA.txt"));
        Connection cnx = HelloApplication.getConnexion();
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery("select ganador, tipo_premio, cantidad from premios where torneo = 'A' order by id, cantidad desc");
        String columnas = "Ganador Premio Cantidad ";
        bw.write(columnas + "\n");
        bw.newLine();
        while (rs.next()) {
            String ganador = rs.getString(1);
            String tipo = rs.getString(2);
            int cantidad = rs.getInt(3);
            bw.write(ganador + " " + tipo + " " + cantidad + "\n");
            bw.newLine();
        }
        bw.close();
        HelloApplication.mensajeExito();
    }

    public void exportarB() throws SQLException, IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("PremiosB.txt"));
        Connection cnx = HelloApplication.getConnexion();
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery("select ganador, tipo_premio, cantidad from premios where torneo = 'B' order by id, cantidad desc");
        String columnas = "Ganador Premio Cantidad ";
        bw.write(columnas + "\n");
        bw.newLine();
        while (rs.next()) {
            String ganador = rs.getString(1);
            String tipo = rs.getString(2);
            int cantidad = rs.getInt(3);
            bw.write(ganador + " " + tipo + " " + cantidad + "\n");
            bw.newLine();
        }
        bw.close();
        HelloApplication.mensajeExito();
    }

}
