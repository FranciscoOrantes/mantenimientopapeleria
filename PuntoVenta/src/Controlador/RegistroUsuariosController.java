/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author flutter
 */
public class RegistroUsuariosController implements Initializable {

    private String username;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String contrasena;
    private String tipoUsuario;
    @FXML
    TextField user, name, ap, am, pass;
    @FXML
    ComboBox comboTipos;
    @FXML
    Button btnRegistrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnRegistrar.setDisable(true);
        comboTipos.getItems().addAll(
                "Administrador",
                "Cajero"
        );
        // TODO
    }

    public void seleccionarTipo() {
        try {
            tipoUsuario = comboTipos.getValue().toString();
            btnRegistrar.setDisable(false);
        } catch (Exception e) {
        }

    }

    public void registrar() throws SQLException {

        if (name.getText().equals("") || ap.getText().equals("") || am.getText().equals("") || pass.getText().equals("")) {
            Alert dialogoAlerta = new Alert(Alert.AlertType.WARNING);
            dialogoAlerta.setTitle("Advertencia");
            dialogoAlerta.setHeaderText("Campos No validos!");
            dialogoAlerta.initStyle(StageStyle.UTILITY);
            dialogoAlerta.showAndWait();
        } else {
            username = user.getText();
            nombre = name.getText();
            apellidoP = ap.getText();
            apellidoM = am.getText();
            contrasena = pass.getText();

            Usuario a = new Usuario();
            a.Guardar(username, contrasena, nombre, apellidoP, apellidoM, tipoUsuario);
            user.setText("");
            name.setText("");
            ap.setText("");
            am.setText("");
            pass.setText("");
            comboTipos.valueProperty().set(null);
            btnRegistrar.setDisable(true);

        }

    }

}
