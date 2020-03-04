package Modelo;

import Controlador.VentasController;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.ObservableList;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Francisco
 */
public class Reporte {
    
    public void generarTicketVenta(String fecha,float total,List<String> ventas2,List<Integer>cantidades,List<Float>precios){
          JRDataSource jr = new JREmptyDataSource();
        try{
        JasperReport ticket = null;
        String path = "/reportes/ticket.jasper";
       
        Map parametro = new HashMap();
        parametro.put("fecha", fecha);
        parametro.put("total", total);
        parametro.put("productos", ventas2);
        parametro.put("cantidades", cantidades);
        parametro.put("precios", precios);
        parametro.put("nombreCajero", VentasController.nombreCajero);
         ticket = (JasperReport) JRLoader.loadObject(getClass().getResource(path));
         
          JasperPrint jprint = JasperFillManager.fillReport(ticket, parametro,jr);
          
          

        JasperViewer view = new JasperViewer(jprint, false);
       
        view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        view.setVisible(true);
            }catch(Exception e){
        System.err.println("Heey " +e);
        
            
            }
       
        
        }
}
