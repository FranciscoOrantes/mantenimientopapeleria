/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.List;
import Modelo.Producto;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author Santiago
 */
public class ListaProductosController implements Initializable {
    // Declaramos los botones
    @FXML private Button anadirBT;
    @FXML private Button modificarBT;
    @FXML private Button eliminarBT;
     // Declaramos los textfileds
    @FXML private TextField nombreTF;
    @FXML private TextField descripcionTF;
    @FXML private TextField precioTF;
    @FXML private TextField folioTF;
    @FXML private TextField tipoTF;
    @FXML private TextField proveedorTF;
    @FXML private TextField indiceBusqueda;
     // Declaramos la tabla y las columnas
    @FXML private TableView<Producto> tablaProducto;
    @FXML private TableColumn nombreCol;
    @FXML private TableColumn descripcionCol;
    @FXML private TableColumn precioCol;
    @FXML private TableColumn folioCol;
    @FXML private TableColumn tipoCol;
    @FXML private TableColumn proveedorCol;
    @FXML private ComboBox filtrado;
    private ObservableList<Producto> productos;
    private int posicionProductoEnTabla;
    private String opcionFiltro,nombre,precio,descripcion;
    private int id,folio;
    @FXML private void anadir(ActionEvent event){
        
    }
    @FXML private void eliminar(ActionEvent event) throws SQLException{
        Producto product = new Producto();
        product.deleteProduct(id);
        inicializarTablaProductos();
        
    }
    @FXML private void modificar(ActionEvent event) throws SQLException{
            nombre = nombreTF.getText();
            precio = precioTF.getText();
            descripcion = descripcionTF.getText();
            folio = Integer.valueOf(folioTF.getText());
        Producto product = new Producto();
        
        product.updateProduct(id,nombre,descripcion,precio,folio);
        nombreTF.clear();
       descripcionTF.clear();
       precioTF.clear();
       folioTF.clear();
        inicializarTablaProductos();
    }
    private final ListChangeListener<Producto> selectorTablaProductos =
            new ListChangeListener<Producto>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends Producto> c) {
                    ponerProductoSeleccionada();
                }
            };

    public Producto getTablaProductosSeleccionada() {
        if (tablaProducto != null) {
            List<Producto> tabla = tablaProducto.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Producto competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }
     public void ponerProductoSeleccionada() {
        
         
        final Producto producto = getTablaProductosSeleccionada();
        posicionProductoEnTabla = productos.indexOf(producto);

        if (producto != null) {

            // Pongo los textFields con los datos correspondientes
            nombreTF.setText(producto.getNombre());
            descripcionTF.setText(producto.getDescripcion());
            precioTF.setText(String.valueOf(producto.getPrecio()));
            folioTF.setText(String.valueOf(producto.getFolio()));
            id=producto.getId();
            
            // Pongo los botones en su estado correspondiente
            modificarBT.setDisable(false);
            eliminarBT.setDisable(false);
            anadirBT.setDisable(true);
            nombreTF.setEditable(true);
            descripcionTF.setEditable(true);
            precioTF.setEditable(true);
            folioTF.setEditable(true);
            

        }
    }
      public void inicializarTablaProductos() {
          productos = FXCollections.observableArrayList();
          Producto.llenarInfoProductos(productos);
          tablaProducto.setItems(productos);
        nombreCol.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
        descripcionCol.setCellValueFactory(new PropertyValueFactory<Producto, String>("descripcion"));
        precioCol.setCellValueFactory(new PropertyValueFactory<Producto, Float>("precio"));
        folioCol.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("folio"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<Producto, String>("tipo"));
        proveedorCol.setCellValueFactory(new PropertyValueFactory<Producto, String>("proveedor"));

        nombreTF.setEditable(false);
       descripcionTF.setEditable(false);
       precioTF.setEditable(false);
       folioTF.setEditable(false);
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       productos = FXCollections.observableArrayList();
       this.inicializarTablaProductos();
       modificarBT.setDisable(true);
       eliminarBT.setDisable(true);
        final ObservableList<Producto> tablaProductoSel = tablaProducto.getSelectionModel().getSelectedItems();
        tablaProductoSel.addListener(selectorTablaProductos);
        filtrado.getItems().addAll(
                "Tipo",
                "Proveedor",
                "Precio"
        );
    }
    public void limpiarSeleccion(){
    tablaProducto.getSelectionModel().clearSelection();
    modificarBT.setDisable(true);
       eliminarBT.setDisable(true);
       anadirBT.setDisable(false);
       nombreTF.setEditable(false);
       descripcionTF.setEditable(false);
       precioTF.setEditable(false);
       folioTF.setEditable(false);
       nombreTF.clear();
       descripcionTF.clear();
       precioTF.clear();
       folioTF.clear();
       
    }
    public void getFilter(){
        opcionFiltro = filtrado.getValue().toString();
        
    }
    public void buscarProducto() throws SQLException {
        String valor= indiceBusqueda.getText();
          productos = FXCollections.observableArrayList();
          Producto.filtradoPrincipal(opcionFiltro,productos,valor);
          tablaProducto.setItems(productos);
        nombreCol.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
        descripcionCol.setCellValueFactory(new PropertyValueFactory<Producto, String>("descripcion"));
        precioCol.setCellValueFactory(new PropertyValueFactory<Producto, String>("precio"));
        folioCol.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("folio"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<Producto, String>("tipo"));
        proveedorCol.setCellValueFactory(new PropertyValueFactory<Producto, String>("proveedor"));

        nombreTF.setEditable(false);
       descripcionTF.setEditable(false);
       precioTF.setEditable(false);
       folioTF.setEditable(false);
        
    }
}