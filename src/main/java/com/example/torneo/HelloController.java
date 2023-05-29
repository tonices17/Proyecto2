package com.example.torneo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private Button botonSalir;
    @FXML
    private TableView<Participantes> clasiA;
    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) botonSalir.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void importButtonAction() throws IOException {
        try {
            datos.importarParticipantes();
            datos.importarClasificacion();
            datos.importarPremios();
            datos.importarbase2();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SuccesImport.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Importación realizada");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }catch (Exception e){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ErrorImport.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("ERROR");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
    }

    @FXML
    private void ButtonA() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("OpenA.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),806,536);
        Stage stage = new Stage();
        stage.setTitle("Open A");
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void mostrarParticipantesA() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ParticipantesA.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Participantes A");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void mostrarClasiA() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ClasificaciónA.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Clasificación A");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void mostrarPremiosA() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PremiosA.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Premios A");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void ButtonB() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("OpenB.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),806,536);
        Stage stage = new Stage();
        stage.setTitle("Open B");
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void mostrarParticipantesB() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ParticipantesB.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Participantes B");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void mostrarClasiB() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ClasificaciónB.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Clasificación B");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void mostrarPremiosB() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PremiosB.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Premios B");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}