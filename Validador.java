
package com.mycompany.proyectofinal2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validador {
public static String normalizarTexto(String texto){
    if (texto == null) return "";
    return texto.trim().toUpperCase();    
}

    /**
     *
     * @param v
     * @return
     * @throws Exception
     */
    public static Venta validarYNormalizar(Venta v) throws Exception {
    if (v == null) throw new Exception("Registro vacio");
    
    v.setIdVenta(normalizarTexto(v.getIdVenta()));
    if (v.getIdVenta().isEmpty()) throw new Exception("ID de venta vacio");
    
    if(v.getFecha() == null) throw new Exception("Fecha invalida o vacia");
    //3
    v.setSucursal(normalizarTexto(v.getSucursal()));
    if (v.getSucursal().isEmpty())throw new Exception("Sucursal vacia");
    //4
    v.setCodigoProducto(normalizarTexto(v.getProducto()));
    if (v.getProducto().isEmpty())throw new Exception("Codigo de producto vacio");
    //5
    v.setProducto(normalizarTexto(v.getProducto()));
    if (v.getCantidad()<=0) throw new Exception("Nombre del producto vacio");
    //6
    if(v.getCantidad()<=0)throw new Exception("La cantidad debe ser mayor a 0");
    //7
   if (v.getPrecioUnitario() <=0) throw new Exception("El precio debe ser mayor a =");
   //8
   v.setVendedor(normalizarTexto(v.getVendedor()));
   if (v.getVendedor().isEmpty()) throw new Exception("Vendedor Vacio");
   
   return v; 
}
}
