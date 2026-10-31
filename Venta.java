package archivos.java;

import java.time.LocalDate;

public class Venta {
    private String idVenta;
    private LocalDate fecha;
    private String sucursal;
    private String codigoProducto;
    private String producto;
    private int cantidad;
    private double precioUnitario;
    private String vendedor;

   
    public Venta() {}

    
    public String getIdVenta() { return idVenta; }
    public void setIdVenta(String idVenta) { this.idVenta = idVenta; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getSucursal() { return sucursal; }
    public void setSucursal(String sucursal) { this.sucursal = sucursal; }
    public String getCodigoProducto() { return codigoProducto; }
    public void setCodigoProducto(String codigoProducto) { this.codigoProducto = codigoProducto; }
    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }
    public String getVendedor() { return vendedor; }
    public void setVendedor(String vendedor) { this.vendedor = vendedor; }
}