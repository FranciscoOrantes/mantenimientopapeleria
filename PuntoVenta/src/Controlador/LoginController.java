/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.InicioSesion;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
     static Stage ventanaInicio;
    static FXMLLoader loaderInicioAdmin;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
private void iniciarSesion(ActionEvent event) throws SQLException{
usuario = txtUsuario.getText();
password = txtPassword.getText();
InicioSesion inicio = new InicioSesion();
inicio.setUsuario(usuario);
inicio.setPassword(password);
inicio.buscarUsuario();

}

public void registrarse() throws IOException{
    
     loaderInicioAdmin = new FXMLLoader(getClass().getResource("/Vista/RegistroUsuarios.fxml"));
                    Parent root1 = (Parent) loaderInicioAdmin.load();
                    ventanaInicio = new Stage();
                    ventanaInicio.setScene(new Scene(root1));
                    ventanaInicio.setResizable(false);
                    ventanaInicio.show();   
}
}
