package entidad;

import java.sql.Date;

// @author TomasReyes
 
public class Pedido {
    
    private int codigo;
    private Producto producto; //llave foranea
    private Date fecha;
    private double precio;
    private int cantidad;
    private double total;

    public Pedido() {
    }

    public Pedido(int codigo, Producto producto, Date fecha, double precio, int cantidad, double total) {
        this.codigo = codigo;
        this.producto = producto;
        this.fecha = fecha;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
