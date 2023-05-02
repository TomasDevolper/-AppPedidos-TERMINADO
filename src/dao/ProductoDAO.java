package dao;

import entidad.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import util.Conexion;

//@author TomasReyes
 
public class ProductoDAO{
    
    Connection cn = Conexion.conectar();

    public void agregar(Producto producto){
        try {
            String sql = "INSERT INTO TBProducto VALUES(?,?,?)"; //no ponemos el codigo porque se genera solo
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString( 1, producto.getNombre() );
            pstm.setDouble( 2, producto.getPrecio() );
            pstm.setInt( 3, producto.getStock() );
            
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATOS AGREGADOS");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public List<Producto> listar(){
        List<Producto> lstProductos = new ArrayList();
        try {
            String sql = "SELECT * FROM TBProducto";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            
            while( rs.next() ){
                int codigo = rs.getInt(1);
                String nombre = rs.getString(2);
                double precio = rs.getDouble(3);
                int stock = rs.getInt(4);
                
                Producto producto = new Producto(codigo, nombre, precio, stock);
                lstProductos.add(producto);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lstProductos;
    }
    
    public Producto buscar(int codigoProducto){
        Producto producto = null;
        try {
            String sql = "SELECT * FROM TBProducto WHERE Codigo = ? ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt( 1, codigoProducto );
            
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()){
                int codigo = rs.getInt(1);
                String nombre = rs.getString(2);
                double precio = rs.getDouble(3);
                int stock = rs.getInt(4);
                
                producto = new Producto(codigo, nombre, precio, stock);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return producto;
    }
    
    public List<Producto> listar(String nombre){
        List<Producto> lstProductos = new ArrayList();
        try {
            String sql = "SELECT * FROM TBProducto WHERE Nombre like ? ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString( 1, "%"+ nombre +"%");
            
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                int codigo = rs.getInt(1);
                nombre = rs.getString(2);
                double precio = rs.getDouble(3);
                int stock = rs.getInt(4);
                
                Producto producto = new Producto(codigo, nombre, precio, stock);
                lstProductos.add(producto);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lstProductos;
    }   
}
