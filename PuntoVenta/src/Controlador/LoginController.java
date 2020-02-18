/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.InicioSesion;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Francisco
 */
public class LoginController implements Initializable {
    @FXML
    TextField txtUsuario;
    @FXML
    PasswordField txtPassword;
    @FXML
    Button btnIniciar;
    @FXML
    Button btnRegistrar;
    private String usuario,password;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
public void iniciarSesion() throws SQLException{
usuario = txtUsuario.getText();
password = txtPassword.getText();
InicioSesion inicio = new InicioSesion();
inicio.setUsuario(usuario);
inicio.setPassword(password);
inicio.buscarUsuario();
}    
    
}
