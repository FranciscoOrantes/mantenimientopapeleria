/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Modelo.Proveedor;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo Lopez
 */
public class ProveedorController {

    Proveedor proveedor = new Proveedor();

    private int id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int telefono;
    private String direccion;
    private String correo;
    private String empresa;

    /* NOTA */
    // Sólo para Probar la funcionalidad -> los datos son estáticos
    private void update() throws SQLException {
        nombre = "Test update";
        apellidoPaterno = "apellido update";
        apellidoMaterno = "apellido update madre";
        telefono = 76543;
        direccion = "Adress";
        correo = "update@gmail.com";
        empresa = "Empresaaaaa update";

        proveedor.updateProveedor(4, nombre, apellidoPaterno, apellidoMaterno, telefono, direccion, correo, empresa);

    }

    /* NOTA */
    // Sólo para Probar la funcionalidad -> el ID  es estático
    private void delete() throws SQLException {
        proveedor.deleteProveedor(6);   // Para eliminar
    }
}