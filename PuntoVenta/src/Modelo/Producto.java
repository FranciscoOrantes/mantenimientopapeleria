/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
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
  ArrayList<Integer> cantidades = new ArrayList<Integer>();
    public Producto(){   
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public float getPrecioProducto() {
        return precioProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
    public String nombreProducto;
    public  float precioProducto;
    public int cantidadProducto;
    public SimpleIntegerProperty id = new SimpleIntegerProperty();
    public SimpleIntegerProperty cantidad = new SimpleIntegerProperty();


    public SimpleStringProperty nombre = new SimpleStringProperty();
    public SimpleStringProperty descripcion = new SimpleStringProperty();
    public SimpleStringProperty precio = new SimpleStringProperty();
    public SimpleIntegerProperty folio = new SimpleIntegerProperty();
    public SimpleStringProperty tipo = new SimpleStringProperty();
    public SimpleStringProperty proveedor = new SimpleStringProperty();
    static String getDataFilter = "SELECT producto.id, producto.nombre,producto.cantidad, producto.descripcion,producto.precio, producto.folio, tipoproducto.tipo,"
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

    public Producto(Integer id, String nombre, String descripcion, Integer cantidad, String precio, Integer folio, String tipo, String proveedor) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.precio = new SimpleStringProperty(precio);
        this.folio = new SimpleIntegerProperty(folio);
        this.tipo = new SimpleStringProperty(tipo);
        this.proveedor = new SimpleStringProperty(proveedor);
        this.cantidad = new SimpleIntegerProperty(cantidad);
    }
    
    public Producto(Integer id, String nombre, String precio, Integer folio,Integer cantidad) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.precio = new SimpleStringProperty(precio);
        this.folio = new SimpleIntegerProperty(folio);
        this.cantidad = new SimpleIntegerProperty(cantidad);
        

    }
    public Producto(String nombre, float precioProducto, int cantidadProducto) {
       
        this.nombreProducto = nombre;
        this.precioProducto = precioProducto;
        this.cantidadProducto = cantidadProducto;
        

    }
    public static void llenarVenta2(List<Producto> lista,Producto producto,List<String>nombresProductos,List<Integer> cantidades,List<Float> precios){
         producto.setNombreProducto(producto.getNombre());
        producto.setPrecioProducto(Float.parseFloat(producto.getPrecio()));
        producto.setCantidadProducto(producto.getCantidad());
        lista.add(new Producto(
        producto.getNombreProducto(),
        producto.getPrecioProducto(),
        producto.getCantidadProducto()
        ));
        nombresProductos.add(producto.getNombreProducto());
        cantidades.add(producto.getCantidadProducto());
        precios.add(producto.getPrecioProducto());
    
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
                    "SELECT producto.id, producto.nombre, producto.descripcion,producto.cantidad, producto.precio, producto.folio,tipoproducto.tipo, proveedor.nombre FROM producto"
                    + " INNER JOIN tipoproducto on tipoproducto.id = producto.tipo_id"
                    + " INNER JOIN proveedor on proveedor.id = producto.proveedor_id");
            rs = pst.executeQuery();
            while (rs.next()) {

                lista.add(
                        new Producto(
                                rs.getInt("producto.id"),
                                rs.getString("producto.nombre"),
                                rs.getString("producto.descripcion"),
                                rs.getInt("producto.cantidad"),
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
                                 rs.getInt("producto.cantidad"),
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
                                 rs.getInt("producto.cantidad"),
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
                                 rs.getInt("producto.cantidad"),
                                rs.getString("producto.precio"),
                                rs.getInt("producto.folio"),
                                rs.getString("tipoproducto.tipo"),
                                rs.getString("proveedor.nombre")
                        )
                );
            }
        
    }
     public void updateProduct(int id, String nombre, String descripcion, String precio,int cantidad, int folio) throws SQLException {
        Conexion con = new Conexion();
        Connection st = con.conectate();

        try {
            
            Statement execute = st.createStatement();
            PreparedStatement pst = st.prepareStatement("UPDATE producto SET nombre = ?, descripcion = ?, precio = ?, cantidad = ?, folio = ? WHERE id = ?");

            pst.setString(1, nombre);
            pst.setString(2, descripcion);
            pst.setString(3, precio);
            pst.setInt(4,cantidad);
            pst.setInt(5, folio);
            
            pst.setInt(6, id);

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
     public void updateCantidad(ObservableList<Producto>productos) throws SQLException {
         System.out.println("TAMAÑO DEL OBSERVABLE LIST " +productos.size());
         int cantidadActualizar = 0;
         int res = 0;
        Conexion con = new Conexion();
        Connection st = con.conectate();
        ResultSet rs;
        try {
           PreparedStatement pstCantidad = st.prepareStatement(
                    "SELECT producto.cantidad FROM producto WHERE id = ? ");
           for (Producto producto1 : productos) {
               pstCantidad.setInt(1, producto1.getId());
            rs = pstCantidad.executeQuery();
            while(rs.next()){
            cantidades.add(rs.getInt("cantidad"));
            }
           }
           
            
            
            Statement execute = st.createStatement();
            PreparedStatement pst = st.prepareStatement("UPDATE producto SET cantidad = ? WHERE id = ?");
            for (Producto producto1 : productos) {
            for(int cantidadBd: cantidades){
                
            cantidadActualizar=cantidadBd-producto1.getCantidad();
            
            }
            
            System.out.println("CANTIDAD QUE SE COMPRO " + producto1.getId() + " " + + producto1.getCantidad());
            System.out.println("CANTIDAD A ACTUALIZAR " + producto1.getId() + " " + cantidadActualizar);
            pst.setInt(1, cantidadActualizar);
             pst.setInt(2, producto1.getId());
             res = pst.executeUpdate();
                
                
            }
            

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
     public void registrarProducto(String nombreCampo,String descripcionCampo,String precioCampo,int cantidadCampo,int folioCampo,int tipoProductoCampo,int tipoProvedorCampo){
        Conexion con = new Conexion();
        Connection st = con.conectate();
        try {
           
            Statement execute = st.createStatement();
            PreparedStatement pst =st.prepareStatement("INSERT INTO producto(nombre,descripcion,precio,cantidad,folio,tipo_id,proveedor_id) VALUES(?,?,?,?,?,?,?)");
            pst.setString(1, nombreCampo);
            pst.setString(2, descripcionCampo);
            pst.setString(3, precioCampo);
            pst.setInt(4, cantidadCampo);
            pst.setInt(5, folioCampo);
            pst.setInt(6, tipoProductoCampo);
            pst.setInt(7, tipoProvedorCampo);
            pst.executeUpdate();
            Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                dialogoAlerta.setTitle("Registro de productos");
                dialogoAlerta.setHeaderText("Correcto");
                dialogoAlerta.setContentText("Ha registrado un producto con éxito");
                dialogoAlerta.initStyle(StageStyle.UTILITY);
                dialogoAlerta.showAndWait();
        } catch (Exception e) {
            System.err.println("Error "+e);
        }
        }
    public static void registrarVentas(ObservableList<Producto>ventas,String fecha,int usuario_id) throws SQLException{
     Conexion con = new Conexion();
     SecureRandom random = new SecureRandom();
 String folio = new BigInteger(130, random).toString(32);
     float monto =0;
        Connection st = con.conectate();
        try {
            Statement execute = st.createStatement();
        PreparedStatement pst =st.prepareStatement("INSERT INTO venta(producto_id,cantidad,monto,fecha,folio,usuario_id) VALUES(?,?,?,?,?,?)");
        for (Producto producto1 : ventas) {
            monto = producto1.getCantidad()*(Float.parseFloat(producto1.getPrecio()));
            System.out.println("Monto " + monto);
            pst.setInt(1, producto1.getId());
            pst.setInt(2, producto1.getCantidad());
            pst.setFloat(3, monto);
            pst.setString(4, fecha);
            pst.setString(5, folio);
            pst.setInt(6, usuario_id);
            pst.executeUpdate();
        }
        }catch(Exception e){
        System.err.println("ERROR " + e);
        }
        }
    public float sumaTotalDia(String fecha) throws SQLException {
        float total = 0;
        Conexion con = new Conexion();
        Connection st = con.conectate();
        ResultSet rs = null;
        try {
           PreparedStatement pstVenta = st.prepareStatement(
                    "SELECT SUM(venta.monto)as suma FROM venta WHERE fecha = ? ");
          pstVenta.setString(1, fecha);
            rs = pstVenta.executeQuery();
            if(rs.next()){
            total = rs.getFloat("suma");
            }
        }catch(Exception e){
        
        }
        return total;
    }
    }


