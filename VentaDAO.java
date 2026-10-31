package mycompany;

import java.sql.*;
import java.util.List;

public class VentaDAO {


    public void guardarVentas(List<Venta> ventas) {
        
        String sql = "INSERT INTO ventas (id_venta, fecha, sucursal, codigo_producto, producto, cantidad, precio_unitario, vendedor) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ON CONFLICT (id_venta) DO NOTHING";
        
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            for (Venta v : ventas) {
                ps.setString(1, v.getIdVenta());
                ps.setDate(2, Date.valueOf(v.getFecha()));
                ps.setString(3, v.getSucursal());
                ps.setString(4, v.getCodigoProducto());
                ps.setString(5, v.getProducto());
                ps.setInt(6, v.getCantidad());
                ps.setDouble(7, v.getPrecioUnitario());
                ps.setString(8, v.getVendedor());
                
                ps.addBatch();
            }
            ps.executeBatch();
            System.out.println("Ventas guardadas en la BD correctamente.");
            
        } catch (SQLException e) {
            System.out.println("Error al guardar ventas en BD: " + e.getMessage());
        }
    }

    
    public void guardarErrores(List<RegistroError> errores) {
        String sql = "INSERT INTO errores (archivo_origen, registro_original, motivo_rechazo) VALUES (?, ?, ?)";
        
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            for (RegistroError err : errores) {
                ps.setString(1, err.getArchivoOrigen());
                ps.setString(2, err.getRegistroOriginal());
                ps.setString(3, err.getMotivoRechazo());
                ps.addBatch();
            }
            ps.executeBatch();
            System.out.println("Errores guardados en la BD correctamente.");
            
        } catch (SQLException e) {
            System.out.println("Error al guardar errores en BD: " + e.getMessage());
        }
    }
}