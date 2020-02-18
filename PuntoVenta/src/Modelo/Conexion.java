/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;


/**
 *DESKTOP-2MHO6AV
 * @author Isurilop
 */
public class Conexion {
    Connection conectar=null;
    public static final String URL = "jdbc:mysql://localhost:3306/papeleriadb";
    public static final String USERNAME = "isuri";
   public static final String PASSWORD = "isuri123";

    
    
    /**
     * Metodo que permite conectarse a la base de datos
     * @return conectar
     */
    public Connection conectate(){ 
    
        try {

            Class.forName("com.mysql.jdbc.Driver");
           conectar= (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
           
           

            
          
    
}catch(Exception e){
    
     Alert dialogoAlerta = new Alert(Alert.AlertType.ERROR);
    dialogoAlerta.setTitle("");
    dialogoAlerta.setHeaderText("Error con la BD");
    dialogoAlerta.initStyle(StageStyle.UTILITY);
    dialogoAlerta.showAndWait();
}
        return conectar;  
     }
}