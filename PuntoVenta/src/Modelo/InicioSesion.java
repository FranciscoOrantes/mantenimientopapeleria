/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.LoginController;
import Controlador.MenuController;
import Controlador.VentasController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 *
 * @author Francisco
 */
public class InicioSesion {
     private String usuario;
    private String password;
    static Stage ventanaInicio;
    static FXMLLoader loaderInicioAdmin;
    

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

   

    public boolean buscarUsuario() throws SQLException {

        Conexion con = new Conexion();
        Connection st = con.conectate();
        ResultSet rs;

        try {
            Statement execute2 = st.createStatement();
            PreparedStatement pst2 = st.prepareStatement(
                    "SELECT * FROM usuario WHERE username= ? And password = ?");
            pst2.setString(1, this.getUsuario());
            pst2.setString(2, this.getPassword());
            rs = pst2.executeQuery();

            if (rs.next()) {
                MenuController.tipoUsuario=rs.getString("tipoUsuario");
                VentasController.nombreCajero=rs.getString("nombre") + " " + rs.getString("apellidoPaterno")+ " "+ rs.getString("apellidoMaterno");
                VentasController.idCajero=rs.getInt("id");
                Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                dialogoAlerta.setTitle("Inicio de sesion");
                dialogoAlerta.setHeaderText("Correcto");
                dialogoAlerta.setContentText("Ha iniciado sesión con éxito");
                dialogoAlerta.initStyle(StageStyle.UTILITY);
                dialogoAlerta.showAndWait();
                return true;
                
                    
            } else {

                Alert dialogoAlerta = new Alert(Alert.AlertType.WARNING);
                dialogoAlerta.setTitle("Advertencia");
                dialogoAlerta.setHeaderText("Datos No Validos");
                dialogoAlerta.setContentText("Usuario o Contraseña incorrecta");
                dialogoAlerta.initStyle(StageStyle.UTILITY);
                dialogoAlerta.showAndWait();
              return false;

            }

        } catch (Exception e) {
            
            Alert dialogoAlerta = new Alert(Alert.AlertType.WARNING);
            dialogoAlerta.setTitle("Error");
            dialogoAlerta.setHeaderText("Ha Ocurrido un error con la BD " +e );

            dialogoAlerta.initStyle(StageStyle.UTILITY);
            dialogoAlerta.showAndWait();
        }

        con.conectate().close();
        return false;
    }
}
