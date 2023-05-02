package dao;

import entidad.Pedido;
import entidad.Producto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import util.Conexion;

//@author TomasReyes

public class PedidoDAO{
    
    Connection cn = Conexion.conectar();

    public void agregar(Pedido pedido){
        try {
            String sql = "{ call sp_agregar(?,?,?) }"; //procedimiento almacenado
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt( 1, pedido.getProducto().getCodigo() ); // buscamos el codigo del producto
            cstm.setInt( 2, pedido.getCantidad() );
            cstm.registerOutParameter( 3, Types.VARCHAR);
            
            cstm.executeUpdate();
            
            String mensaje = cstm.getString(3);
            JOptionPane.showMessageDialog(null, mensaje);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public List<Pedido> listar(){
        List<Pedido> lstPedidos = new ArrayList();
        try {
            String sql = "SELECT * FROM TBPedido";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()){
                int codigo = rs.getInt(1);
                int codigoProducto = rs.getInt(2);
                Date fecha = rs.getDate(3);
                double precio = rs.getDouble(4);
                int cantidad = rs.getInt(5);
                double total = rs.getDouble(6);
                
                Producto producto = new ProductoDAO().buscar(codigoProducto);
                
                Pedido pedido = new Pedido(codigo, producto, fecha, precio, cantidad, total);
                lstPedidos.add(pedido);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lstPedidos;
    }
}
