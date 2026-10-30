
package com.mycompany.proyectofinal2;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LectorCSV {
    public static List<Venta> leer(String ruta, List<RegistroError> errores){
        List<Venta> ventas = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(ruta))){
            String[] linea;
            boolean primeraLinea = true;
            while ((linea = reader.readNext()) !=null){
                if (primeraLinea) { primeraLinea = false; continue; }
                
                try{
                    
                    Venta v = new Venta();
                    v.setIdVenta(linea[0]);
                    v.setFecha(LocalDate.parse(linea[1]));
                    v.setSucursal(linea[2]);
                    v.setCodigoProducto(linea[3]);
                    v.setProducto(linea[4]);
                    v.setCantidad(Integer.parseInt(linea[5]));
                    v.setPrecioUnitario(Double.parseDouble(linea[6]));
                    v.setVendedor(linea[7]);
                    
                    ventas.add(Validador.validarYNormalizar(v));                                                    
                }catch (Exception e){
                    errores.add(new RegistroError(ruta, String.join(",", linea),e.getMessage()));
                }
            }
        }catch (Exception e){
            System.out.println("Error leyendo CSV:" + e.getMessage());
        }
        return ventas;
    }

}
