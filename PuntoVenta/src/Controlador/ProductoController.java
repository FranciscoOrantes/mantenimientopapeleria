/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.Producto;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;



/**
 * FXML Controller class
 *
 * @author enriq
 */
public class ProductoController implements Initializable {
    private String nombreCampo;
    private String descripcionCampo;
    private String precioCampo;
    private int folioCampo;
    private int tipoProductoCampo;
    private int tipoProvedorCampo;
    ArrayList<String> lista1 = new ArrayList<String>();
    ArrayList<String> lista2 = new ArrayList<String>();
    @FXML
    private TextField nombre,descripcion,precio,folio;
    @FXML
    private ComboBox tipoProducto,tipoProvedor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        llenarDatosProvedor();
        llenarDatosTiposProductos();
        
    }  
    
    public void registrarProducto(){
         nombreCampo=  nombre.getText();
         descripcionCampo = descripcion.getText();
         precioCampo = precio.getText(); 
         folioCampo = Integer.valueOf(folio.getText());
         String var=(String) tipoProducto.getValue();
         String[] dato = var.split(" ");
         
         String var1=(String) tipoProvedor.getValue();
         String[] dato1 = var1.split(" ");
         tipoProductoCampo = Integer.valueOf(dato[0]);
         tipoProvedorCampo = Integer.valueOf(dato1[0]);
         Producto a= new Producto();
         a.registrarProducto(nombreCampo,descripcionCampo,precioCampo,folioCampo,tipoProductoCampo,tipoProvedorCampo);
          
    }
    public void llenarDatosProvedor(){
        Conexion con = new Conexion();
        Connection st = con.conectate();
        
        ResultSet rs;
        
        try {
            Statement execute = st.createStatement();
            PreparedStatement pst = st.prepareStatement("SELECT * FROM proveedor");
            rs = pst.executeQuery();
            while(rs.next()){
                lista1.add(rs.getString("id")+" "+rs.getString("nombre"));
            };
            tipoProvedor.getItems().addAll(lista1);
        } catch (Exception e) {
            System.err.println("Error Llenar datos");
        }
        
    }
    public void llenarDatosTiposProductos(){
        Conexion con = new Conexion();
        Connection st = con.conectate();
        
        ResultSet rs;
        
        try {
            Statement execute = st.createStatement();
            PreparedStatement pst = st.prepareStatement("SELECT * FROM tipoproducto");
            rs = pst.executeQuery();
            while(rs.next()){
                lista2.add(rs.getString("id")+" "+rs.getString("tipo"));
            };
            tipoProducto.getItems().addAll(lista2);
        } catch (Exception e) {
            System.err.println("Error Llenar datos");
        }
    }
    
}