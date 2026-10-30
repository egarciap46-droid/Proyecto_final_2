
package com.mycompany.proyectofinal2;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LectorXML {
   public static List<Venta> leer(String ruta, List<RegistroError> errores) {
        List<Venta> ventas = new ArrayList<>();
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(ruta));
            doc.getDocumentElement().normalize();

            NodeList listaNodos = doc.getElementsByTagName("venta");
            
            for (int i = 0; i <listaNodos.getLength(); i++){
                Node nodo = listaNodos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE){
                    Element elemento = (Element) nodo;
                    try{
                        Venta v = new Venta();
                        v.setIdVenta(elemento.getElementsByTagName("id").item(0).getTextContent());
                        v.setSucursal(elemento.getElementsByTagName("sucursal").item(0).getTextContent());
                        v.setCodigoProducto(elemento.getElementsByTagName("codigo").item(0).getTextContent());
                        v.setProducto(elemento.getElementsByTagName("producto").item(0).getTextContent());
                        v.setCantidad(Integer.parseInt(elemento.getElementsByTagName("cantidad").item(0).getTextContent()));  
                        v.setPrecioUnitario(Double.parseDouble(elemento.getElementsByTagName("precio").item(0).getTextContent()));
                        v.setVendedor(elemento.getElementsByTagName("vendedor").item(0).getTextContent());
                        
                         ventas.add(Validador.validarYNormalizar(v));
                    }catch (Exception e){
                        errores.add(new RegistroError(ruta, "Nodo XML"+ i,e.getMessage()));
                    }
                }
            }
} catch (Exception e){
    System.out.println("ERRROR leyendo XML" + e.getMessage());
}
        return ventas;
}
}
