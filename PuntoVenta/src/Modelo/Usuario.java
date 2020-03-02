/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author Ibraham Jimenez
 */
public class Usuario {

    Conexion con = new Conexion();
    Connection st = con.conectate();
    
    public void Guardar(String username,String password,String nombre,String ap,String am,String tipo) throws SQLException{
        Statement execute = st.createStatement();
        PreparedStatement pst = st.prepareStatement(
                    "INSERT INTO usuario(username,password,nombre,apellidoPaterno,apellidoMaterno,tipoUsuario) VALUES(?,?,?,?,?,?)");
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, nombre);
            pst.setString(4, ap);
            pst.setString(5, am);
            pst.setString(6, tipo);
            
            int res = pst.executeUpdate();
            if (res > 0) {
                  Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
    dialogoAlerta.setTitle("Exito");
    dialogoAlerta.setHeaderText("Se han guardado los Datos");
    dialogoAlerta.initStyle(StageStyle.UTILITY);
    dialogoAlerta.showAndWait();
            }

    
    
    }
    
    
}

