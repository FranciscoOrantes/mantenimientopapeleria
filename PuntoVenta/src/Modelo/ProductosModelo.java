package Modelo;
import java.sql.*;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author enriq
 */
public class ProductosModelo {
    String nombre="",descripcion="";
    int tipoProducto,tipoProvedor,folio;
    float precio;
    
    
    
    
    public void registrarProducto(String nombreCampo,String descripcionCampo,String precioCampo,int folioCampo,int tipoProductoCampo,int tipoProvedorCampo){
        System.out.println("Entro a este producto");
        Conexion con = new Conexion();
        Connection st = con.conectate();
        try {
            System.out.println("Aca");
            Statement execute = st.createStatement();
            PreparedStatement pst =st.prepareStatement("INSERT INTO producto(nombre,descripcion,precio,folio,tipo_id,proveedor_id) VALUES(?,?,?,?,?,?)");
            pst.setString(1, nombreCampo);
            pst.setString(2, descripcionCampo);
            pst.setString(3, precioCampo);
            pst.setInt(4, folioCampo);
            pst.setInt(5, tipoProductoCampo);
            pst.setInt(6, tipoProvedorCampo);
            pst.executeUpdate();
            Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                dialogoAlerta.setTitle("Registro de productos");
                dialogoAlerta.setHeaderText("Correcto");
                dialogoAlerta.setContentText("Ha registrado un producto con éxito");
                dialogoAlerta.initStyle(StageStyle.UTILITY);
                dialogoAlerta.showAndWait();
        } catch (Exception e) {
            System.err.println("Error");
        }
        }
}
