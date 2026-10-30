
package com.mycompany.proyectofinal2;

import com.google.gson.*;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import java.util.Collection;

public class LectorJSON {
    public static List<Venta> leer(String ruta, List<RegistroError> errores){
        List<Venta> ventas = new ArrayList<>();
        try{
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class,
                    (JsonSerializer<LocalDate>)(src, type, context) -> new JsonPrimitive(src.toString()))
                    
                    .registerTypeAdapter(LocalDate.class,
                    (JsonDeserializer<LocalDate>)(json, type, context)-> LocalDate.parse(json.getAsString()))
            .create();
            
           Type listaTipo = new TypeToken<ArrayList<Venta>>(){}.getType();
            List<Venta> crudas = gson.fromJson(new FileReader(ruta), listaTipo);
            
            for (Venta v: crudas){
                try{
                     ventas.add(Validador.validarYNormalizar(v));
                
                }catch (Exception e) {
                    errores.add(new RegistroError(ruta, v.toString(), e.getMessage()));
            }
        }
    }  catch (Exception e) {
         System.out.println("Error leyendo JSON: " + e.getMessage());
    }
        return ventas;
}

}