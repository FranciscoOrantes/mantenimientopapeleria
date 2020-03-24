package Controlador;

import static Controlador.LoginController.loaderInicioAdmin;
import static Controlador.LoginController.ventanaInicio;
import Modelo.Proveedor;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Marcelo Lopez
 */
public class ProveedorController {

    private String nombreCampo, apellidoPaternoCampo, apellidoMaternoCampo, direccionCampo, correoCampo, empresaCampo;
    private String telefonoCampo;
    @FXML
    private TextField nombre, apellidoPaterno, apellidoMaterno, telefono, empresa, correo, direccion;

    Proveedor proveedor = new Proveedor();


    /* NOTA */
    // Sólo para Probar la funcionalidad -> los datos son estáticos

    /* NOTA */
    // Sólo para Probar la funcionalidad -> el ID  es estático
    private void delete() throws SQLException {
        proveedor.deleteProveedor(6);   // Para eliminar
    }

    @FXML
    private void registrarProveedor(ActionEvent event) {
        nombreCampo = nombre.getText();
        apellidoPaternoCampo = apellidoPaterno.getText();
        apellidoMaternoCampo = apellidoMaterno.getText();
        telefonoCampo = telefono.getText();
        empresaCampo = empresa.getText();
        correoCampo = correo.getText();
        direccionCampo = direccion.getText();

        Proveedor a = new Proveedor();
        a.registrarProveedor(nombreCampo, apellidoPaternoCampo, apellidoMaternoCampo, telefonoCampo, empresaCampo, correoCampo, direccionCampo);
        limpiar();
    }

    public void limpiar() {
        nombre.setText("");
        apellidoPaterno.setText("");
        apellidoMaterno.setText("");
        telefono.setText("");
        empresa.setText("");
        correo.setText("");
        direccion.setText("");
    }

    @FXML
    private void cerrar(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        loaderInicioAdmin = new FXMLLoader(getClass().getResource("/Vista/ListaProveedores.fxml"));
        Parent root1 = (Parent) loaderInicioAdmin.load();
        ventanaInicio = new Stage();
        ventanaInicio.setScene(new Scene(root1));
        ventanaInicio.setResizable(false);
        ventanaInicio.show();

    }
}
