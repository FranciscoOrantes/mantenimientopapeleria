/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXMLLoader;
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
  /*    private String usuario;
    private int password;
    private int passwordNueva;
    static Stage ventanaInicio;
    static Stage ventanaLogin;
    static FXMLLoader loaderInicioAdmin;
    static int inicioSesionBd;
    static int passwordAntigua;
    static String user;
    public static int idUsuarioActivo;

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    

    public String getUsuario() {
        return usuario;
    }

    public int getPassword() {
        return password;
    }

   

    public void buscarUsuario(String usuario, String password) throws SQLException {

        Conexion con = new Conexion();
        Connection st = con.conectate();
        ResultSet rs;

        try {
            Statement execute2 = st.createStatement();
            PreparedStatement pst2 = st.prepareStatement(
                    "SELECT * FROM usuario WHERE username= ? And password = ?");
            pst2.setString(1, usuario);
            pst2.setString(2, password);
            rs = pst2.executeQuery();

            if (rs.next()) {
                System.out.println("HA INICIADO SESION CON EXITO");
            } else {
                System.out.println("HA INICIADO SESION CON ERRORES :(");
                Alert dialogoAlerta = new Alert(Alert.AlertType.WARNING);
                dialogoAlerta.setTitle("Advertencia");
                dialogoAlerta.setHeaderText("Datos No Validos");
                dialogoAlerta.setContentText("Usuario o Contraseña incorrecta");
                dialogoAlerta.initStyle(StageStyle.UTILITY);
                dialogoAlerta.showAndWait();
                loaderInicioAdmin = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root1 = (Parent) loaderInicioAdmin.load();
                ventanaInicio = new Stage();
                ventanaInicio.setScene(new Scene(root1));
                ventanaInicio.setResizable(false);
                ventanaInicio.show();

            }

        } catch (Exception e) {
             System.out.println("HA INICIADO SESION CON ERRORES :(");
            JOptionPane.showMessageDialog(null, e);
            Alert dialogoAlerta = new Alert(Alert.AlertType.WARNING);
            dialogoAlerta.setTitle("Error");
            dialogoAlerta.setHeaderText("Ha Ocurrido un error con la BD");

            dialogoAlerta.initStyle(StageStyle.UTILITY);
            dialogoAlerta.showAndWait();
        }

        con.conectate().close();
    }*/

    
}
