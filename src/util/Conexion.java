package util;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

// @author TomasReyes
 
public class Conexion {
    
    public static Connection conectar(){
        Connection cn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DataBaseName=BDPedidos;USer=sa;Password=sqladmin;");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR EN BDConexion:\n"+e);
        }
        return cn;
    }
    
}
