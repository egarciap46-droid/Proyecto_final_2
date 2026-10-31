package com.mycompany.proyectofinal2;

import java.util.List;

public class ProyectoFinal2 {



public class Main {
  public static void main(String[] args){
    
  String rutaCSV = "ventas.csv";
        String rutaJSON = "ventas.json";
        String rutaXML = "ventas.xml";  

  System.out.println("=== INICIANDO PROCESAMIENTO ETL ===\n");
    
  ResultadoProcesamiento resultado = ProcesadorDatos.procesar(rutaCSV, rutaJSON, rutaXML); 
    List<Venta> ventasValidas = resultado.getVentasValidas();
        List<RegistroError> errores = resultado.getErrores();
        int totalRecibidos = resultado.getTotalRecibidos();
        int duplicados = resultado.getTotalDuplicados();

   System.out.println("Registros válidos listos para BD: " + ventasValidas.size());
        System.out.println("Registros con error detectados: " + errores.size());
        System.out.println("Registros duplicados detectados: " + duplicados);
     
    VentaDAO ventaDAO = new VentaDAO();
        ventaDAO.guardarVentas(ventasValidas);
        ventaDAO.guardarErrores(errores);

 AnalisisDAO analisis = new AnalisisDAO();
    analisis.obtenerResumen();
        analisis.top3Vendedores();
        analisis.ventasPorSucursal();

 generarReporteTexto(ventasValidas, errores, totalRecibidos, duplicados, analisis);
    }  
private static void generarReporteTexto(List<Venta> ventas, List<RegistroError> errores, 
                                            int totalRecibidos, int duplicados, AnalisisDAO analisis) {
        
        ReporteTXT reporte = new ReporteTXT();
        reporte.generarReporteErrores(errores);

   StringBuilder sb = new StringBuilder();
   double montoTotal = 0;
        Venta ventaMayor = null;
        Venta ventaMenor = null;

        for (Venta v : ventas) {
            double montoVenta = v.getCantidad() * v.getPrecioUnitario();
            montoTotal += montoVenta;

            if (ventaMayor == null || montoVenta > (ventaMayor.getCantidad() * ventaMayor.getPrecioUnitario())) {
                ventaMayor = v;
            }
            if (ventaMenor == null || montoVenta < (ventaMenor.getCantidad() * ventaMenor.getPrecioUnitario())) {
                ventaMenor = v;
            }
        }

        double promedio = ventas.isEmpty() ? 0 : montoTotal / ventas.size();
        double porcentajeRechazados = totalRecibidos == 0 ? 0 : ((double)(errores.size() + duplicados) / totalRecibidos) * 100;

   sb.append("--- RESUMEN GENERAL ---\n");
        sb.append("Total registros recibidos (aprox): ").append(totalRecibidos).append("\n");
        sb.append("Total aceptados: ").append(ventas.size()).append("\n");
        sb.append("Total rechazados: ").append(errores.size()).append("\n");
        sb.append("Total duplicados: ").append(duplicados).append("\n");
        sb.append(String.format("Porcentaje de registros rechazados: %.2f%%\n\n", porcentajeRechazados));

        sb.append("--- MONTOS ---\n");
        sb.append(String.format("Monto total vendido: Q%.2f\n", montoTotal));
        sb.append(String.format("Promedio por transacción: Q%.2f\n", promedio));
        
        if (ventaMayor != null) {
            sb.append(String.format("Transacción MAYOR: ID %s - Q%.2f\n", ventaMayor.getIdVenta(), (ventaMayor.getCantidad() * ventaMayor.getPrecioUnitario())));
        }
        if (ventaMenor != null) {
            sb.append(String.format("Transacción MENOR: ID %s - Q%.2f\n", ventaMenor.getIdVenta(), (ventaMenor.getCantidad() * ventaMenor.getPrecioUnitario())));
        }

     sb.append("\n--- CONCLUSIONES ---\n");
        sb.append("1. (Escribe tu conclusión sobre los errores encontrados)\n");
        sb.append("2. (Escribe tu conclusión sobre la sucursal o vendedor top)\n");
        sb.append("3. (Escribe tu conclusión sobre el producto más vendido)\n");

  reporte.generarReporteAnalisis(sb.toString());
        
        System.out.println("\n=== PROCESO FINALIZADO CORRECTAMENTE ===");
    }
}
}
