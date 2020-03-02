/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Producto;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ibraham Jimenez
 */
public class VentasController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TableView<Producto> tablaProducto;
    @FXML private TableColumn nombreCol;
    @FXML private TableColumn precioCol;
    @FXML private TableColumn folioCol;
    
    @FXML private TableView<Producto> tablaVenta;
    @FXML private TableColumn nombreVenta;
    @FXML private TableColumn precioVenta;
    @FXML private TableColumn folioVenta;
    @FXML private TableColumn cantidadVenta;
    @FXML private Button anadir;
    @FXML private Label fechaVenta;
    @FXML private Label totalVenta;
    java.util.Date fecha = new Date();

    
    private ObservableList<Producto> productos;
    private ObservableList<Producto> ventas;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       productos = FXCollections.observableArrayList();
       ventas = FXCollections.observableArrayList();
       this.inicializarTablaProductos();
       fechaVenta.setText(fecha.toString());
      
        
        // TODO
    }    
    
      public void inicializarTablaProductos() {
        productos = FXCollections.observableArrayList();
        Producto.llenarInfoProductos(productos);
        tablaProducto.setItems(productos);
        nombreCol.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
        precioCol.setCellValueFactory(new PropertyValueFactory<Producto, Float>("precio"));
        folioCol.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("Folio"));
       
        
    }
      
          public Producto getTablaProductosSeleccionada() {
        if (tablaProducto != null) {
            List<Producto> tabla = tablaProducto.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Producto competicionSeleccionada = tabla.get(0);
                //JOptionPane.showMessageDialog(null, competicionSeleccionada.id);
                return competicionSeleccionada;
            }
        }
        return null;
    }
         
          
     public void llenarTablaVenta(){
         TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Ventas");
        dialog.setContentText("Ingresa la cantidad:");
         Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
        final Producto producto = getTablaProductosSeleccionada();
        producto.setCantidad(Integer.valueOf(result.get()));
        Producto.llenarVenta(ventas,producto);
        tablaVenta.setItems(ventas);
        nombreVenta.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
        precioVenta.setCellValueFactory(new PropertyValueFactory<Producto, Float>("precio"));
        folioVenta.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("Folio"));
        cantidadVenta.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("cantidad"));
        float res=0;
            for (Producto producto1 : ventas) {
               res = res+(producto1.getCantidad()*Float.parseFloat(producto1.getPrecio()));
                
                
            }
            totalVenta.setText(String.valueOf(res));
            

        }
        //result.ifPresent(name -> System.out.println("Your name: " + name));
        
     } 
     
     
     public void endVenta(){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       alert.setTitle("Confirmar");
       alert.setHeaderText("");
       alert.setContentText("Esta seguro de continuar?");

       Optional<ButtonType> result = alert.showAndWait();
       if (result.get() == ButtonType.OK){
          
       } else {
           
       }
            }
    
}
