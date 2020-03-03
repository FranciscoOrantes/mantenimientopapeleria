package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
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
    public SimpleStringProperty telefono = new SimpleStringProperty();
    public SimpleStringProperty direccion = new SimpleStringProperty();
    public SimpleStringProperty correo = new SimpleStringProperty();
    public SimpleStringProperty empresa = new SimpleStringProperty();
    static String getDataFilter = "SELECT * FROM proveedor ";

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

    public String getTelefono() {
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

    public Proveedor(Integer id, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String direccion, String correo, String empresa) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidoPaterno = new SimpleStringProperty(apellidoPaterno);
        this.apellidoMaterno = new SimpleStringProperty(apellidoMaterno);
        this.telefono = new SimpleStringProperty(telefono);
        this.direccion = new SimpleStringProperty(direccion);
        this.correo = new SimpleStringProperty(correo);
        this.empresa = new SimpleStringProperty(empresa);
    }
    public static void llenarInfoProveedores(ObservableList<Proveedor> lista) {
        Conexion con = new Conexion();
        Connection st = con.conectate();
        ResultSet rs;

        try {
            Statement execute = st.createStatement();

            PreparedStatement pst = st.prepareStatement(
                    "SELECT * FROM proveedor");
            rs = pst.executeQuery();
            while (rs.next()) {

                lista.add(
                        new Proveedor(
                                rs.getInt("proveedor.id"),
                                rs.getString("proveedor.nombre"),
                                rs.getString("proveedor.apellidoPaterno"),
                                rs.getString("proveedor.apellidoMaterno"),
                                rs.getString("proveedor.telefono"),
                                rs.getString("proveedor.direccion"),
                                rs.getString("proveedor.correo"),
                                rs.getString("proveedor.empresa")
                        )
                );

            }

        } catch (Exception e) {
            System.err.println("excetpcion " + e);

        }
    }
    public static void filtradoPrincipal(String filtrado,ObservableList<Proveedor> lista,String valor) throws SQLException {
        System.out.println("el valor fue "+filtrado);
        if (filtrado.equals("Nombre")) {
           filtradoNombre(lista,valor);
        }
        if(filtrado.equals("Telefono")){
            filtradoTelefono(lista,valor);
        }
        if(filtrado.equals("Empresa")){
            filtradoEmpresa(lista,valor);
        }
    }
    public static void filtradoNombre(ObservableList<Proveedor> lista,String nombre) throws SQLException {
        Conexion con = new Conexion();
        Connection st = con.conectate();
        ResultSet rs;
        Statement execute = st.createStatement();
        PreparedStatement pst = st.prepareStatement(getDataFilter + "WHERE proveedor.nombre LIKE '%" + nombre + "%'");
        rs = pst.executeQuery();
        while (rs.next()) {
            lista.add(
                        new Proveedor(
                                rs.getInt("proveedor.id"),
                                rs.getString("proveedor.nombre"),
                                rs.getString("proveedor.apellidoPaterno"),
                                rs.getString("proveedor.apellidoMaterno"),
                                rs.getString("proveedor.telefono"),
                                rs.getString("proveedor.direccion"),
                                rs.getString("proveedor.correo"),
                                rs.getString("proveedor.empresa")
                        )
                );
        }
    }
    public static void filtradoTelefono(ObservableList<Proveedor> lista,String telefonoI) throws SQLException {
        Conexion con = new Conexion();
        Connection st = con.conectate();
        ResultSet rs;
        Statement execute = st.createStatement();
        PreparedStatement pst = st.prepareStatement(getDataFilter + "where proveedor.telefono LIKE '%" + telefonoI +"%'");
        rs = pst.executeQuery();
        try {
            while (rs.next()) {
                lista.add(
                        new Proveedor(
                                rs.getInt("proveedor.id"),
                                rs.getString("proveedor.nombre"),
                                rs.getString("proveedor.apellidoPaterno"),
                                rs.getString("proveedor.apellidoMaterno"),
                                rs.getString("proveedor.telefono"),
                                rs.getString("proveedor.direccion"),
                                rs.getString("proveedor.correo"),
                                rs.getString("proveedor.empresa")
                        )
                );
            }
        } catch (SQLException e) {
        }
    }
    public static void filtradoEmpresa(ObservableList<Proveedor> lista,String empresa) throws SQLException {
         Conexion con = new Conexion();
        Connection st = con.conectate();
        ResultSet rs;
        Statement execute = st.createStatement();
        PreparedStatement pst = st.prepareStatement(getDataFilter + "Where proveedor.empresa LIKE '%" + empresa + "%'");
        rs = pst.executeQuery();
        while (rs.next()) {
                lista.add(
                        new Proveedor(
                                rs.getInt("proveedor.id"),
                                rs.getString("proveedor.nombre"),
                                rs.getString("proveedor.apellidoPaterno"),
                                rs.getString("proveedor.apellidoMaterno"),
                                rs.getString("proveedor.telefono"),
                                rs.getString("proveedor.direccion"),
                                rs.getString("proveedor.correo"),
                                rs.getString("proveedor.empresa")
                        )
                );
            }
        
    }
    public void updateProveedor(int id, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String direccion, String correo, String empresa) throws SQLException {
        Conexion con = new Conexion();
        Connection st = con.conectate();

        try {
            Statement execute = st.createStatement();
            PreparedStatement pst = st.prepareStatement("UPDATE proveedor SET nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, telefono = ?, direccion = ?, correo = ?, empresa = ? WHERE id = ?");

            pst.setString(1, nombre);
            pst.setString(2, apellidoPaterno);
            pst.setString(3, apellidoMaterno);
            pst.setString(4, telefono);
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
    
    public void registrarProveedor(String nombreCampo,String apellidoPaternoCampo,String apellidoMaternoCampo,String telefonoCampo,String empresaCampo,String correoCampo,String direccionCampo){
        Conexion con = new Conexion();
        Connection st = con.conectate();
        try {
            Statement execute = st.createStatement();
            PreparedStatement pst =st.prepareStatement("INSERT INTO proveedor(nombre,apellidoPaterno,apellidoMaterno,telefono,direccion,correo,empresa) VALUES(?,?,?,?,?,?,?)");
            pst.setString(1, nombreCampo);
            pst.setString(2, apellidoPaternoCampo);
            pst.setString(3, apellidoMaternoCampo);
            pst.setString(4, telefonoCampo);
            pst.setString(5, direccionCampo);
            pst.setString(6, correoCampo);
            pst.setString(7, empresaCampo);
            pst.executeUpdate();
            Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                dialogoAlerta.setTitle("Registro de productos");
                dialogoAlerta.setHeaderText("Correcto");
                dialogoAlerta.setContentText("Ha registrado un proveedor con éxito");
                dialogoAlerta.initStyle(StageStyle.UTILITY);
                dialogoAlerta.showAndWait();
        } catch (Exception e) {
            System.err.println("Error " + e);
        }
        }
}
