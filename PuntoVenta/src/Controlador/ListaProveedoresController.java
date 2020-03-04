/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Modelo.Proveedor;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
public class ListaProveedoresController implements Initializable {
    // Declaramos los botones
    @FXML private Button anadirBT;
    @FXML private Button modificarBT;
    @FXML private Button eliminarBT;
     // Declaramos los textfileds
    @FXML private TextField nombreTF;
    @FXML private TextField apellidoPTF;
    @FXML private TextField apellidoMTF;
    @FXML private TextField telefonoTF;
    @FXML private TextField direccionTF;
    @FXML private TextField correoTF;
    @FXML private TextField empresaTF;
    @FXML private TextField indiceBusqueda;
     // Declaramos la tabla y las columnas
    @FXML private TableView<Proveedor> tablaProveedor;
    @FXML private TableColumn nombreCol;
    @FXML private TableColumn apellidoPaternoCol;
    @FXML private TableColumn apellidoMaternoCol;
    @FXML private TableColumn telefonoCol;
    @FXML private TableColumn direccionCol;
    @FXML private TableColumn correoCol;
    @FXML private TableColumn empresaCol;
    @FXML private ComboBox filtrado;
    private ObservableList<Proveedor> proveedores;
    private int posicionProveedorEnTabla;
    private String nombre,apellidoP,apellidoM,telefono,direccion,correo,empresa;
    private  String opFiltro="";
    private int id;
    static Stage ventanaInicio;
    static FXMLLoader loaderInicioAdmin;
    @FXML private void anadir(ActionEvent event) throws IOException{
        
       loaderInicioAdmin = new FXMLLoader(getClass().getResource("/Vista/RegistroProveedor.fxml"));
                    Parent root1 = (Parent) loaderInicioAdmin.load();
                    ventanaInicio = new Stage();
                    ventanaInicio.setScene(new Scene(root1));
                    ventanaInicio.setResizable(false);
                    ventanaInicio.show(); 
    }
    @FXML private void eliminar(ActionEvent event) throws SQLException{
        Proveedor proveedor = new Proveedor();
        proveedor.deleteProveedor(id);
        inicializarTablaProveedor();
        
    }
    @FXML private void modificar(ActionEvent event) throws SQLException{
            nombre = nombreTF.getText();
            apellidoP=apellidoPTF.getText();
            apellidoM=apellidoMTF.getText();
            telefono = telefonoTF.getText();
            direccion = direccionTF.getText();
            correo = correoTF.getText();
            empresa=empresaTF.getText();
            
        Proveedor proveedor = new Proveedor();
        
        proveedor.updateProveedor(id,nombre,apellidoP,apellidoM,telefono,direccion,correo,empresa);
        nombreTF.clear();
       apellidoPTF.clear();
       apellidoMTF.clear();
       telefonoTF.clear();
       direccionTF.clear();
       correoTF.clear();
       empresaTF.clear();
       inicializarTablaProveedor();
    }
    private final ListChangeListener<Proveedor> selectorTablaProveedores=
            new ListChangeListener<Proveedor>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends Proveedor> c) {
                    ponerProveedorSeleccionado();
                }
            };
    public Proveedor getTablaProveedorSeleccionado() {
        if (tablaProveedor != null) {
            List<Proveedor> tabla = tablaProveedor.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Proveedor competicionSeleccionado = tabla.get(0);
                return competicionSeleccionado;
            }
        }
        return null;
    }public void ponerProveedorSeleccionado() {
        
         
        final Proveedor proveedor = getTablaProveedorSeleccionado();
        posicionProveedorEnTabla = proveedores.indexOf(proveedor);

        if (proveedor != null) {

            // Pongo los textFields con los datos correspondientes
            nombreTF.setText(proveedor.getNombre());
            apellidoPTF.setText(proveedor.getApellidoPaterno());
            apellidoMTF.setText(proveedor.getApellidoMaterno());
            telefonoTF.setText(proveedor.getTelefono());
            direccionTF.setText(proveedor.getDireccion());
            correoTF.setText(proveedor.getCorreo());
            empresaTF.setText(proveedor.getEmpresa());
            id=proveedor.getId();
            
            // Pongo los botones en su estado correspondiente
            modificarBT.setDisable(false);
            eliminarBT.setDisable(false);
            anadirBT.setDisable(true);
            nombreTF.setEditable(true);
            apellidoPTF.setEditable(true);
            apellidoMTF.setEditable(true);
            telefonoTF.setEditable(true);
            direccionTF.setEditable(true);
            correoTF.setEditable(true);
            empresaTF.setEditable(true);
            

        }
    } 
    public void inicializarTablaProveedor() {
          proveedores = FXCollections.observableArrayList();
          Proveedor.llenarInfoProveedores(proveedores);
          tablaProveedor.setItems(proveedores);
        nombreCol.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("nombre"));
        apellidoPaternoCol.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("apellidoPaterno"));
        apellidoMaternoCol.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("apellidoMaterno"));
        telefonoCol.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("telefono"));
        direccionCol.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("direccion"));
        correoCol.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("correo"));
        empresaCol.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("empresa"));

        nombreTF.setEditable(false);
        apellidoPTF.setEditable(false);
        apellidoMTF.setEditable(false);
        telefonoTF.setEditable(false);
        direccionTF.setEditable(false);
        correoTF.setEditable(false);
        empresaTF.setEditable(false);
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       proveedores = FXCollections.observableArrayList();
       this.inicializarTablaProveedor();
       modificarBT.setDisable(true);
       eliminarBT.setDisable(true);
       final ObservableList<Proveedor> tablaProveedorSel = tablaProveedor.getSelectionModel().getSelectedItems();
       tablaProveedorSel.addListener(selectorTablaProveedores);
       filtrado.getItems().addAll(
                "Nombre",
                "Telefono",
                "Empresa"
        );
    }    
    public void limpiarSeleccion(){
    tablaProveedor.getSelectionModel().clearSelection();
    modificarBT.setDisable(true);
    eliminarBT.setDisable(true);
    anadirBT.setDisable(false);
    nombreTF.setEditable(false);
    apellidoPTF.setEditable(false);
    apellidoMTF.setEditable(false);
    telefonoTF.setEditable(false);
    direccionTF.setEditable(false);
    correoTF.setEditable(false);
    empresaTF.setEditable(false);
    nombreTF.clear();
    apellidoPTF.clear();
    apellidoMTF.clear();
    telefonoTF.clear();
    direccionTF.clear();
    correoTF.clear();
    empresaTF.clear();
       
    }
    public void getFilter(){
    
           try {
            opFiltro = filtrado.getValue().toString();
        } catch (Exception e) {
              
        }

        
        
        
    }
    public void buscarProveedor() throws SQLException {
        String valor= indiceBusqueda.getText();
        proveedores = FXCollections.observableArrayList();
        Proveedor.filtradoPrincipal(opFiltro,proveedores,valor);
        tablaProveedor.setItems(proveedores);
        nombreCol.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("nombre"));
        apellidoPaternoCol.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("apellidoPaterno"));
        apellidoMaternoCol.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("apellidoMaterno"));
        telefonoCol.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("telefono"));
        direccionCol.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("direccion"));
        correoCol.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("correo"));
        empresaCol.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("empresa"));

        nombreTF.setEditable(false);
        apellidoPTF.setEditable(false);
        apellidoMTF.setEditable(false);
        telefonoTF.setEditable(false);
        direccionTF.setEditable(false);
        correoTF.setEditable(false);
        empresaTF.setEditable(false);
        filtrado.valueProperty().set(null);
        
    }
}