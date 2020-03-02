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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author Marcelo Lopez
 */
public class Proveedor {

    public Proveedor() {
    }

    public SimpleIntegerProperty id = new SimpleIntegerProperty();
    public SimpleStringProperty nombre = new SimpleStringProperty();
    public SimpleStringProperty apellidoPaterno = new SimpleStringProperty();
    public SimpleStringProperty apellidoMaterno = new SimpleStringProperty();
    public SimpleIntegerProperty telefono = new SimpleIntegerProperty();
    public SimpleStringProperty direccion = new SimpleStringProperty();
    public SimpleStringProperty correo = new SimpleStringProperty();
    public SimpleStringProperty empresa = new SimpleStringProperty();

    public Integer getId() {
        return id.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getApellidoPaterno() {
        return apellidoPaterno.get();
    }

    public String getApellidoMaterno() {
        return apellidoMaterno.get();
    }

    public Integer getTelefono() {
        return telefono.get();
    }

    public String getDireccion() {
        return direccion.get();
    }

    public String getCorreo() {
        return correo.get();
    }

    public String getEmpresa() {
        return empresa.get();
    }

    public Proveedor(Integer id, String nombre, String apellidoPaterno, String apellidoMaterno, Integer telefono, String direccion, String correo, String empresa) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidoPaterno = new SimpleStringProperty(apellidoPaterno);
        this.apellidoMaterno = new SimpleStringProperty(apellidoMaterno);
        this.telefono = new SimpleIntegerProperty(telefono);
        this.direccion = new SimpleStringProperty(direccion);
        this.correo = new SimpleStringProperty(correo);
        this.empresa = new SimpleStringProperty(empresa);
    }

    
    
    /*  --- NOTA --- */
// Lo tengo instanciado en la Clase PuntoVenta(carpeta Main) -> Sólo para prueba -> Code en la linea: 41
    public void updateProveedor(int id, String nombre, String apellidoPaterno, String apellidoMaterno, int telefono, String direccion, String correo, String empresa) throws SQLException {
        Conexion con = new Conexion();
        Connection st = con.conectate();

        try {
            Statement execute = st.createStatement();
            PreparedStatement pst = st.prepareStatement("UPDATE proveedor SET nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, telefono = ?, direccion = ?, correo = ?, empresa = ? WHERE id = ?");

            pst.setString(1, nombre);
            pst.setString(2, apellidoPaterno);
            pst.setString(3, apellidoMaterno);
            pst.setInt(4, telefono);
            pst.setString(5, direccion);
            pst.setString(6, correo);
            pst.setString(7, empresa);
            pst.setInt(8, id);

            int res = pst.executeUpdate();

            if (res > 0) {
                Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                dialogoAlerta.setTitle("Exito");
                dialogoAlerta.setHeaderText("Se han actualizado los Datos");
                dialogoAlerta.initStyle(StageStyle.UTILITY);
                dialogoAlerta.showAndWait();
            }

        } catch (Exception e) {
            System.err.println("EXCEPCION " + e);
            Alert dialogoAlerta = new Alert(Alert.AlertType.ERROR);
            dialogoAlerta.setTitle("Error");
            dialogoAlerta.setHeaderText("Ha ocurrido un error con la Base de Datos");
            dialogoAlerta.initStyle(StageStyle.UTILITY);
            dialogoAlerta.showAndWait();
        }
        st.close();
    }

    public void deleteProveedor(int id) throws SQLException {
        try {
            Conexion con = new Conexion();
            Connection st = con.conectate();
            ResultSet rs;
            Statement execute = st.createStatement();
            String query1 = "delete from proveedor " + "where id=" + id;
            execute.executeUpdate(query1);
            System.out.println("Borrado con éxito");
        } catch (SQLException e) {
        }
    }
}