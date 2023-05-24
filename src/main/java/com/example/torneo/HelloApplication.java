package com.example.torneo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static com.example.torneo.conexion.getConnexion;

public class HelloApplication extends Application {

    static Connection cnx;
    static {
        try {
            cnx = getConnexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("BenidormChess.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Benidorm Chess OPEN 2022");
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        BaseDatos.importarbase();
        launch();
    }


}