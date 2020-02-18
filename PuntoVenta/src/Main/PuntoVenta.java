/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Modelo.Conexion;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Francisco
 */
public class PuntoVenta extends Application {
    static Stage ventanaLogin;
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.ventanaLogin = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/ListaProductos.fxml"));
        Scene scene = new Scene(root);
        ventanaLogin.setScene(scene);
        ventanaLogin.setResizable(false);
        ventanaLogin.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
        Conexion conection= new Conexion();
        conection.conectate();
    }
    
}
