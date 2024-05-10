
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 * @author Core-i5
 */
public class ConsultasLogin {
    
    /*
    public void MostrarUsuariosCombo(JComboBox comboUsuario) {
        
        modelo.Conexion objetoConexion = new modelo.Conexion();
        
        String sql = "SELECT * FROM USUARIOS";
        
        Statement st;
        
        try{
            //st = objetoConexion.getConexion().createStatement();
            st = objetoConexion.getConexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            comboUsuario.removeAllItems();
            
          
            while (rs.next()) {
                String nombreUsuario = rs.getString("nombres");
                this.establecerIdUsuario(rs.getInt("id_usuario"));
                    
                comboUsuario.addItem(nombreUsuario);
                comboUsuario.putClientProperty(nombreUsuario,id_Usuario);
            } 

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al mostrar Usuario"+e.toString());
        }
        
        finally {
            objetoConexion.cerrarConexion();
        }
    }
    */
}
