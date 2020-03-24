/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Producto;
import Modelo.Reporte;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Francisco
 */
public class MenuController implements Initializable {

    @FXML
    private ImageView btnVentas;
    @FXML
    private ImageView btnProductos;
    @FXML
    private ImageView btnProveedores;
    @FXML
    private ImageView btnReportes;
    @FXML
    private ImageView btnUsuarios;
    @FXML
    private Text txtVentas;
    @FXML
    private Text txtProductos;
    @FXML
    private Text txtProveedores;
    @FXML
    private Text txtReportes;
    @FXML
    private Text txtUsuarios;
    static Stage opcionMenu;
    static FXMLLoader loaderMenu;
    public static String tipoUsuario;
    float total;
    Date fecha;
    String fechaString;
    // Declaramos la tabla y las columnas

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SimpleDateFormat objSDF = new SimpleDateFormat("dd-MMM-yyyy");

        fecha = new Date();
        fechaString = objSDF.format(fecha).toString();
        if (tipoUsuario.equals("Cajero")) {
            btnReportes.setVisible(false);
            btnUsuarios.setVisible(false);
            txtReportes.setVisible(false);
            txtUsuarios.setVisible(false);
        }
    }

    public void abrirVentas(Event event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        loaderMenu = new FXMLLoader(getClass().getResource("/Vista/Ventas.fxml"));
        Parent root1 = (Parent) loaderMenu.load();
        opcionMenu = new Stage();
        opcionMenu.setScene(new Scene(root1));
        opcionMenu.setResizable(false);
        opcionMenu.show();
    }

    public void abrirUsuarios() throws IOException {
        loaderMenu = new FXMLLoader(getClass().getResource("/Vista/RegistroUsuarios.fxml"));
        Parent root1 = (Parent) loaderMenu.load();
        opcionMenu = new Stage();
        opcionMenu.setScene(new Scene(root1));
        opcionMenu.setResizable(false);
        opcionMenu.show();
    }

    public void abrirProveedores(Event event) throws IOException {
        //DIRIGIRSE A LISTA PROVEEDORES
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        loaderMenu = new FXMLLoader(getClass().getResource("/Vista/ListaProveedores.fxml"));
        Parent root1 = (Parent) loaderMenu.load();
        opcionMenu = new Stage();
        opcionMenu.setScene(new Scene(root1));
        opcionMenu.setResizable(false);
        opcionMenu.show();
    }

    public void abrirInventario() throws IOException {
        loaderMenu = new FXMLLoader(getClass().getResource("/Vista/ListaProductos.fxml"));
        Parent root1 = (Parent) loaderMenu.load();
        opcionMenu = new Stage();
        opcionMenu.setScene(new Scene(root1));
        opcionMenu.setResizable(false);
        opcionMenu.show();
    }

    public void abrirReportes() throws SQLException {
        System.err.println("FALTA REPORTES");
        Producto producto = new Producto();
        total = producto.sumaTotalDia(fechaString);
        Reporte reporte = new Reporte();
        reporte.generarCorteCaja(fechaString, total);
    }

}
