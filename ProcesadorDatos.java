
package com.mycompany.proyectofinal2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProcesadorDatos {
public static ResultadoProcesamiento procesar(String rutaCSV, String rutaJSON, String rutaXML) {
    List<RegistroError> errores = new ArrayList<>();
    List<Venta> todasLasVentas = new ArrayList<>();
    
    todasLasVentas.addAll(LectorCSV.leer(rutaCSV, errores));
    todasLasVentas.addAll(LectorJSON.leer(rutaJSON, errores));
    todasLasVentas.addAll(LectorXML.leer(rutaXML, errores));
    
     List<Venta> ventasValidas = new ArrayList<>();
        Set<String> idsVistos = new HashSet<>();
        int duplicados = 0;
        
         for (Venta v : todasLasVentas) {
            if (idsVistos.contains(v.getIdVenta())) {
                duplicados++;
                errores.add(new RegistroError("Múltiples archivos", v.getIdVenta(), "Registro duplicado"));
            } else {
                idsVistos.add(v.getIdVenta());
                ventasValidas.add(v);
}   
}
         ResultadoProcesamiento resultado = new ResultadoProcesamiento();
        resultado.setVentasValidas(ventasValidas);
        resultado.setErrores(errores);
        resultado.setTotalRecibidos(todasLasVentas.size() + errores.size());
        
 resultado.setTotalDuplicados(duplicados);
        
        return resultado;
}
}
