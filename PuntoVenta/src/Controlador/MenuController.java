/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Francisco
 */
public class MenuController implements Initializable {
    @FXML private ImageView btnVentas;
    @FXML private ImageView btnProductos;
    @FXML private ImageView btnProveedores;
    @FXML private ImageView btnReportes;
    @FXML private ImageView btnUsuarios;
    @FXML private Text txtVentas;
    @FXML private Text txtProductos;
    @FXML private Text txtProveedores;
    @FXML private Text txtReportes;
    @FXML private Text txtUsuarios;
    public static String tipoUsuario;
    
     // Declaramos la tabla y las columnas
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(tipoUsuario.equals("Cajero")){
        btnReportes.setVisible(false);
        btnUsuarios.setVisible(false);
        txtReportes.setVisible(false);
        txtUsuarios.setVisible(false);
        }
    }    
    
}
