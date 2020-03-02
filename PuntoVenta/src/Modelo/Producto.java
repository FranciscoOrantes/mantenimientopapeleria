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
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author Santiago
 */
public class Producto {
  
    public Producto(){
        
    }

    public SimpleIntegerProperty id = new SimpleIntegerProperty();
    public SimpleIntegerProperty cantidad = new SimpleIntegerProperty();


    public SimpleStringProperty nombre = new SimpleStringProperty();
    public SimpleStringProperty descripcion = new SimpleStringProperty();
    public SimpleStringProperty precio = new SimpleStringProperty();
    public SimpleIntegerProperty folio = new SimpleIntegerProperty();
    public SimpleStringProperty tipo = new SimpleStringProperty();
    public SimpleStringProperty proveedor = new SimpleStringProperty();
    static String getDataFilter = "SELECT producto.id, producto.nombre, producto.descripcion,producto.precio, producto.folio, tipoproducto.tipo,"
            + " proveedor.nombre FROM producto\n"
            + "INNER JOIN tipoproducto on tipoproducto.id = producto.tipo_id\n"
            + "INNER JOIN proveedor on proveedor.id = producto.proveedor_id ";

    public void setCantidad(int cantidad) {
        this.cantidad = new SimpleIntegerProperty(cantidad);
    }
    public Integer getId() {
        return id.get();

    }
    public Integer getCantidad() {
        return cantidad.get();

    }

    public String getNombre() {
        return nombre.get();

    }

    public String getDescripcion() {
        return descripcion.get();

    }

    public String getPrecio() {
        return precio.get();

    }

    public Integer getFolio() {
        return folio.get();

    }

    public String getTipo() {
        return tipo.get();

    }

    public String getProveedor() {
        return proveedor.get();

    }

    public Producto(Integer id, String nombre, String descripcion, String precio, Integer folio, String tipo, String proveedor) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.precio = new SimpleStringProperty(precio);
        this.folio = new SimpleIntegerProperty(folio);
        this.tipo = new SimpleStringProperty(tipo);
        this.proveedor = new SimpleStringProperty(proveedor);

    }
    
    public Producto(Integer id, String nombre, String precio, Integer folio,Integer cantidad) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.precio = new SimpleStringProperty(precio);
        this.folio = new SimpleIntegerProperty(folio);
        this.cantidad = new SimpleIntegerProperty(cantidad);
        

    }
    public static void llenarVenta(ObservableList<Producto> lista,Producto producto){
        lista.add(new Producto(
        producto.getId(),
        producto.getNombre(),
        producto.getPrecio(),
        producto.getFolio(),
        producto.getCantidad()
        ));
    
    
    }
    public static void llenarInfoProductos(ObservableList<Producto> lista) {
        Conexion con = new Conexion();
        Connection st = con.conectate();
        ResultSet rs;

        try {
            Statement execute = st.createStatement();

            PreparedStatement pst = st.prepareStatement(
                    "SELECT producto.id, producto.nombre, producto.descripcion, producto.precio, producto.folio,tipoproducto.tipo, proveedor.nombre FROM producto"
                    + " INNER JOIN tipoproducto on tipoproducto.id = producto.tipo_id"
                    + " INNER JOIN proveedor on proveedor.id = producto.proveedor_id");
            rs = pst.executeQuery();
            while (rs.next()) {

                lista.add(
                        new Producto(
                                rs.getInt("producto.id"),
                                rs.getString("producto.nombre"),
                                rs.getString("producto.descripcion"),
                                rs.getString("producto.precio"),
                                rs.getInt("producto.folio"),
                                rs.getString("tipoproducto.tipo"),
                                rs.getString("proveedor.nombre")
                        )
                );

            }

        } catch (Exception e) {
            System.err.println("excetpcion " + e);

        }
    }

    public static void filtradoPrincipal(String filtrado,ObservableList<Producto> lista,String valor) throws SQLException {
        if (filtrado.equals("Tipo")) {
           filtradoCategoria(lista,valor);
        }
        if(filtrado.equals("Proveedor")){
            FiltradoProveedores(lista,valor);
        }
        if(filtrado.equals("Precio")){
            filtradoPrecio(lista,valor);
        }
    }

    public static void filtradoCategoria(ObservableList<Producto> lista,String categoria) throws SQLException {
        Conexion con = new Conexion();
        Connection st = con.conectate();
        ResultSet rs;
        Statement execute = st.createStatement();
        PreparedStatement pst = st.prepareStatement(getDataFilter + "Where tipoproducto.tipo LIKE '%" + categoria + "%'");
        rs = pst.executeQuery();
        while (rs.next()) {
            lista.add(
                        new Producto(
                                rs.getInt("producto.id"),
                                rs.getString("producto.nombre"),
                                rs.getString("producto.descripcion"),
                                rs.getString("producto.precio"),
                                rs.getInt("producto.folio"),
                                rs.getString("tipoproducto.tipo"),
                                rs.getString("proveedor.nombre")
                        )
                );
        }
    }
     public static void filtradoPrecio(ObservableList<Producto> lista,String precioI) throws SQLException {
        Conexion con = new Conexion();
        Connection st = con.conectate();
        ResultSet rs;
        Statement execute = st.createStatement();
        PreparedStatement pst = st.prepareStatement(getDataFilter + "where producto.precio LIKE '%" + precioI +"%'");
        rs = pst.executeQuery();
        try {
            while (rs.next()) {
                lista.add(
                        new Producto(
                                rs.getInt("producto.id"),
                                rs.getString("producto.nombre"),
                                rs.getString("producto.descripcion"),
                                rs.getString("producto.precio"),
                                rs.getInt("producto.folio"),
                                rs.getString("tipoproducto.tipo"),
                                rs.getString("proveedor.nombre")
                        )
                );
            }
        } catch (SQLException e) {
        }
    }
     public static void FiltradoProveedores(ObservableList<Producto> lista,String proveedor) throws SQLException {
         Conexion con = new Conexion();
        Connection st = con.conectate();
        ResultSet rs;
        Statement execute = st.createStatement();
        PreparedStatement pst = st.prepareStatement(getDataFilter + "Where proveedor.nombre LIKE '%" + proveedor + "%'");
        rs = pst.executeQuery();
        while (rs.next()) {
                lista.add(
                        new Producto(
                                rs.getInt("producto.id"),
                                rs.getString("producto.nombre"),
                                rs.getString("producto.descripcion"),
                                rs.getString("producto.precio"),
                                rs.getInt("producto.folio"),
                                rs.getString("tipoproducto.tipo"),
                                rs.getString("proveedor.nombre")
                        )
                );
            }
        
    }
     public void updateProduct(int id, String nombre, String descripcion, String precio, int folio) throws SQLException {
        Conexion con = new Conexion();
        Connection st = con.conectate();

        try {
            Statement execute = st.createStatement();
            PreparedStatement pst = st.prepareStatement("UPDATE producto SET nombre = ?, descripcion = ?, precio = ?, folio = ? WHERE id = ?");

            pst.setString(1, nombre);
            pst.setString(2, descripcion);
            pst.setString(3, precio);
            pst.setInt(4, folio);
            
            pst.setInt(5, id);

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
            dialogoAlerta.setHeaderText("Ha ocurrido un error con la BD");
            dialogoAlerta.initStyle(StageStyle.UTILITY);
            dialogoAlerta.showAndWait();
        }
        st.close();
    }
     public void deleteProduct(int id) throws SQLException {
    
        try {
            Conexion con = new Conexion();
        Connection st = con.conectate();
        ResultSet rs;
        Statement execute = st.createStatement();
            String query1 = "delete from producto " + "where id=" + id;
            execute.executeUpdate(query1);
            System.out.println("Borrado con éxito");
        } catch (SQLException e) {
        }
    }
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
