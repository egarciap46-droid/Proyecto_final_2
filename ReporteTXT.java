package mycompany;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReporteTXT {

    public void generarReporteErrores(List<RegistroError> errores) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("errores_procesamiento.txt"))) {
            bw.write("--- REPORTE DE ERRORES ---\n");
            bw.write("Total de errores: " + errores.size() + "\n\n");
            
            for (RegistroError err : errores) {
                bw.write("Archivo: " + err.getArchivoOrigen() + "\n");
                bw.write("Registro: " + err.getRegistroOriginal() + "\n");
                bw.write("Motivo: " + err.getMotivoRechazo() + "\n");
                bw.write("----------------------------------------\n");
            }
            System.out.println("Archivo errores_procesamiento.txt generado.");
        } catch (IOException e) {
            System.out.println("Error al escribir archivo de errores: " + e.getMessage());
        }
    }

    public void generarReporteAnalisis(String resumenTexto) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("reporte_analisis.txt"))) {
            bw.write("--- REPORTE DE ANÁLISIS DE VENTAS ---\n");
            bw.write(resumenTexto);
            System.out.println("Archivo reporte_analisis.txt generado.");
        } catch (IOException e) {
            System.out.println("Error al escribir reporte de análisis: " + e.getMessage());
        }
    }
}