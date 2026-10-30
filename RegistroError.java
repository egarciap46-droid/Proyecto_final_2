
package com.mycompany.proyectofinal2;

public class RegistroError {
private String archivoOrigen;
private String datosOriginales;
private String motivo;

public RegistroError(String archivoOrigen, String datosOriginales, String motivo){
    this.archivoOrigen = archivoOrigen;
    this.datosOriginales = datosOriginales;
    this.motivo = motivo; 
}

public String getArchivoOrigen() {return archivoOrigen;}
public String getDatosOriginales() {return datosOriginales;}
public String getMotivo() {return motivo;}
}
