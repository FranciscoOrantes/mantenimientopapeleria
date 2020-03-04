/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.net.URL;
import java.util.List;
import Modelo.Producto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
    @FXML private TextField cantidadTF;
    @FXML private TextField folioTF;
    @FXML private TextField tipoTF;
    @FXML private TextField proveedorTF;
    @FXML private TextField indiceBusqueda;
     // Declaramos la tabla y las columnas
    @FXML private TableView<Producto> tablaProducto;
    @FXML private TableColumn nombreCol;
    @FXML private TableColumn descripcionCol;
    @FXML private TableColumn precioCol;
    @FXML private TableColumn cantidadCol;
    @FXML private TableColumn folioCol;
    @FXML private TableColumn tipoCol;
    @FXML private TableColumn proveedorCol;
    @FXML private ComboBox filtrado;
    private ObservableList<Producto> productos;
    private int posicionProductoEnTabla;
    private String opcionFiltro,nombre,precio,descripcion;
    private int id,folio,cantidad;
    static Stage ventanaInicio;
    static FXMLLoader loaderInicioAdmin;
    @FXML private void anadir(ActionEvent event) throws IOException{
        
       loaderInicioAdmin = new FXMLLoader(getClass().getResource("/Vista/RegistroProducto.fxml"));
                    Parent root1 = (Parent) loaderInicioAdmin.load();
                    ventanaInicio = new Stage();
                    ventanaInicio.setScene(new Scene(root1));
                    ventanaInicio.setResizable(false);
                    ventanaInicio.show(); 
    }
    @FXML private void eliminar(ActionEvent event) throws SQLException{
        Producto product = new Producto();
        product.deleteProduct(id);
        inicializarTablaProductos();
        
    }
    @FXML private void modificar(ActionEvent event) throws SQLException{
            nombre = nombreTF.getText();
            precio = precioTF.getText();
            cantidad =Integer.valueOf(cantidadTF.getText());
            descripcion = descripcionTF.getText();
            folio = Integer.valueOf(folioTF.getText());
        Producto product = new Producto();
        
        product.updateProduct(id,nombre,descripcion,precio,cantidad,folio);
        nombreTF.clear();
       descripcionTF.clear();
       precioTF.clear();
       cantidadTF.clear();
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
            cantidadTF.setText(String.valueOf(producto.getCantidad()));
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
            cantidadTF.setEditable(true);
            

        }
    } 
     
      public void inicializarTablaProductos() {
          productos = FXCollections.observableArrayList();
          Producto.llenarInfoProductos(productos);
          tablaProducto.setItems(productos);
        nombreCol.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
        descripcionCol.setCellValueFactory(new PropertyValueFactory<Producto, String>("descripcion"));
        precioCol.setCellValueFactory(new PropertyValueFactory<Producto, Float>("precio"));
        cantidadCol.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("cantidad"));
        folioCol.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("folio"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<Producto, String>("tipo"));
        proveedorCol.setCellValueFactory(new PropertyValueFactory<Producto, String>("proveedor"));

        nombreTF.setEditable(false);
       descripcionTF.setEditable(false);
       precioTF.setEditable(false);
       folioTF.setEditable(false);
       cantidadTF.setEditable(false);
        
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
       cantidadTF.setEditable(false);
       folioTF.setEditable(false);
       nombreTF.clear();
       descripcionTF.clear();
       precioTF.clear();
       folioTF.clear();
       cantidadTF.clear();
       
    }
    public void getFilter(){
        try {
            opcionFiltro = filtrado.getValue().toString();
        } catch (Exception e) {
        }
        
        
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
        cantidadCol.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("cantidad"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<Producto, String>("tipo"));
        proveedorCol.setCellValueFactory(new PropertyValueFactory<Producto, String>("proveedor"));

        nombreTF.setEditable(false);
       descripcionTF.setEditable(false);
       precioTF.setEditable(false);
       folioTF.setEditable(false);
       cantidadTF.setEditable(false);
       filtrado.valueProperty().set(null);
        
    }
}