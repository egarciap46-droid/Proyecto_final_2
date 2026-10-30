
package com.mycompany.proyectofinal2;

import java.util.List; 

public class ResultadoProcesamiento {
   private List<Venta> ventasValidas;
   private List<RegistroError> errores;
   private int totalRecibidos;
   private int totalDuplicados;
   
   public List<Venta> getVentasValidas(){return ventasValidas;}
   public void setVentasValidas(List<Venta> ventasValidas){this.ventasValidas = ventasValidas;}
   
   public List<RegistroError> getErrores(){return errores;}
   public void setErrores(List<RegistroError> errores) {this.errores = errores;}
   
   public int getTotalRecibidos() {return totalRecibidos;}
   public void setTotalRecibidos(int totalRecibidos) {this.totalRecibidos = totalRecibidos;}
   
   public int getTotalDuplicados() {return totalDuplicados;}
   public void setTotalDuplicados(int totalDuplicados) {this.totalDuplicados = totalDuplicados;}
   
}
