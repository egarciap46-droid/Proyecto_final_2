package com.ejemplo;

import java.sql.*;

public class AnalisisDAO {
    
 
    public void obtenerResumen() {
        String sql = "SELECT COUNT(*) as total_aceptados, SUM(cantidad * precio_unitario) as monto_total FROM ventas";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                System.out.println("Total Aceptados: " + rs.getInt("total_aceptados"));
                System.out.println("Monto Total Vendido: Q" + rs.getDouble("monto_total"));
            }
        } catch (SQLException e) {
            System.out.println("Error en resumen: " + e.getMessage());
        }
    }

    
    public void top3Vendedores() {
        String sql = "SELECT vendedor, SUM(cantidad * precio_unitario) as total " +
                     "FROM ventas GROUP BY vendedor ORDER BY total DESC LIMIT 3";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            System.out.println("\n--- TOP 3 VENDEDORES ---");
            while (rs.next()) {
                System.out.println(rs.getString("vendedor") + " - Q" + rs.getDouble("total"));
            }
        } catch (SQLException e) {
            System.out.println("Error en top vendedores: " + e.getMessage());
        }
    }
    
    // 3. Ventas por sucursal
    public void ventasPorSucursal() {
        String sql = "SELECT sucursal, SUM(cantidad * precio_unitario) as total " +
                     "FROM ventas GROUP BY sucursal";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            System.out.println("\n--- VENTAS POR SUCURSAL ---");
            while (rs.next()) {
                System.out.println(rs.getString("sucursal") + " - Q" + rs.getDouble("total"));
            }
        } catch (SQLException e) {
            System.out.println("Error en sucursales: " + e.getMessage());
        }
    }
}