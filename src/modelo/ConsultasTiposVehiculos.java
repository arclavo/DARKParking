
package modelo;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 * @author DChangC
 */
public class ConsultasTiposVehiculos extends Conexion{
    int id_TipoVehiculo;

    // Constructor
    public void establecerIdTipoVehiculo(int id_TipoVehiculo) {
        this.id_TipoVehiculo = id_TipoVehiculo;
    }
    
    public void MostrarTiposVehiculosCombo(JComboBox comboTipoVehiculo) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConexion();
            String sql = "SELECT * FROM TIPO_VEHICULOS WHERE estado='ACTIVO'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            comboTipoVehiculo.removeAllItems();

            while (rs.next()) {
                String descripcionTipoVehiculo = rs.getString("descripcion");
                this.establecerIdTipoVehiculo(rs.getInt("id_tipovehiculo"));
                
                comboTipoVehiculo.addItem(descripcionTipoVehiculo);
                comboTipoVehiculo.putClientProperty(descripcionTipoVehiculo,id_TipoVehiculo);
            } 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al mostrar Tipo de Vehículo: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                // Manejar excepciones al cerrar recursos
                JOptionPane.showMessageDialog(null,"Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
    
    // Grabar Registro nuevo en la BD
    public boolean registrar(TiposVehiculos tipveh) {    
        // Inicializa variables
        PreparedStatement ps = null;
        Connection con = getConexion();

     // String sql = "INSERT INTO tipos_vehiculos (codigo, descripcion, estado) VALUES(?,?,?)";
        String sql = "INSERT INTO tipos_vehiculos (descripcion, estado) VALUES(?,?)";

        // Manejo de excepciones
        try {
            ps = con.prepareStatement(sql);  // Prepara la consulta

            // Establece valores de parámetros en una consulta preparada
         // ps.setString(1, tipveh.getCodigo());
            ps.setString(1, tipveh.getDescripcion());
            ps.setString(2, tipveh.getEstado());   
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println(ex);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
    }

    // Modifica un registro existente en la BD
    public boolean modificar(TiposVehiculos tipveh) {
        // Inicializa variables
        PreparedStatement ps = null;
        Connection con = getConexion();

     // String sql = "UPDATE tipos_vehiculos SET codigo=?, descripcion=?, estado=? WHERE id_tipovehiculo=?";
        String sql = "UPDATE tipos_vehiculos SET descripcion=?, estado=? WHERE id_tipovehiculo=?";

        // Manejo de excepciones
        try {
            ps = con.prepareStatement(sql);

            // Establece valores de parámetros en una consulta preparada
         // ps.setString(1, tipveh.getCodigo());
            ps.setString(1, tipveh.getDescripcion());
            ps.setString(2, tipveh.getEstado());
            ps.setInt(3, tipveh.getId_TipoVehiculo());
            
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println(ex);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
    }

    // Elimina un registro existente en la BD
    public boolean eliminar(TiposVehiculos tipveh) {
        // Inicializa variables
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM tipos_vehiculos WHERE id_tipovehiculo=?";

        // Manejo de excepciones
        try {
            ps = con.prepareStatement(sql);
            
            // Establece valores de parámetros en una consulta preparada
            ps.setInt(1, tipveh.getId_TipoVehiculo());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println(ex);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
    }

    /*
    // Busca un registro en la BD por su código
    public boolean buscar(TiposVehiculos tipveh) {
        // Inicializa variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM tipos_vehiculos WHERE codigo=?";

        // Manejo de excepciones
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, tipveh.getCodigo());
            rs = ps.executeQuery();

            if (rs.next()) {
                // Establece valores de parámetros en una consulta preparada
                tipveh.setId_TipoVehiculo(Integer.parseInt(rs.getString("id_tipovehiculo")));
             // tipveh.setCodigo(rs.getString("codigo"));
                tipveh.setDescripcion(rs.getString("descripcion"));
                tipveh.setEstado(rs.getString("estado"));                
                return true;
            }
            return false;
        } catch (SQLException ex) {
            System.err.println(ex);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
    } 
    */
    
    /*
    // Verifica si existe un registro con el mismo código en la BD
    public boolean existeCodigo(String codigo) {
        // Inicializa variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT COUNT(*) AS count FROM tipos_vehiculos WHERE codigo=?";

        // Manejo de excepciones
        try {
            ps = con.prepareStatement(sql); // Prepara la consulta sql
            ps.setString(1, codigo);   // Establece valor parámetro para la consulta
            rs = ps.executeQuery();  // Ejecuta la consulta en la BD

            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
            return false;
        } catch (SQLException ex) {
            System.err.println(ex);
            return false;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                con.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
    }    
    */
    
   // Muestra "todos" los usuarios de la tabla en el formulario (JTable)
    public void MostrarTiposVehiculos(JTable tbTiposVehiculos) throws SQLException {
       
        // Inicializa variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        
        String sql = "SELECT id_tipovehiculo,descripcion,estado FROM tipos_vehiculos";
        
        // Creo cabeceras de columnas de la Tabla en el formulario
        modelo.addColumn("Id");
        modelo.addColumn("Descripción");
        modelo.addColumn("Estado");
        
        tbTiposVehiculos.setModel(modelo);
        
        // Cambiar tamaño de fuente y tipo de fuente para la tabla
        tbTiposVehiculos.setFont(new Font("Tahoma", Font.PLAIN, 9));
        
        // Cambiar tamaño de fuente y tipo de fuente para los títulos de las columnas
        JTableHeader header = tbTiposVehiculos.getTableHeader();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tbTiposVehiculos.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER); // Centra el texto de los títulos
        Font headerFont = new Font("Tahoma", Font.BOLD, 9); // Por ejemplo, tamaño 12 en negrita
        header.setFont(headerFont);

        // Cambiar tamaño de columna
        TableColumnModel columnModel = tbTiposVehiculos.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(10);  //Id
        columnModel.getColumn(1).setPreferredWidth(100); //Descripción
        columnModel.getColumn(2).setPreferredWidth(20);  //Estado
        
        try {
            ps = con.prepareStatement(sql); // Prepara la consulta sql
            rs = ps.executeQuery();  // Ejecuta la consulta en la BD
            
            while (rs.next()) {
                String id_tipovehiculo = rs.getString("id_tipovehiculo");
                String descripcion = rs.getString("descripcion");                
                String estado = rs.getString("estado");
                
                modelo.addRow(new Object[]{id_tipovehiculo, descripcion, estado});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al ejecutar la consulta, Error: "+e.toString());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }  
    
    // SELECCIONA REGISTRO DE LA TABLA EN FORMULARIO Y MUESTRA EN LOS CAMPOS
    public void Seleccionar(JTable totalTiposVehiculos, JTextField id, JTextField descripcion, JComboBox estado) {        
     
        int fila = totalTiposVehiculos.getSelectedRow();
        
        if (fila >= 0) {
            id.setText(totalTiposVehiculos.getValueAt(fila,0).toString());
            descripcion.setText(totalTiposVehiculos.getValueAt(fila,1).toString());
            // Obtener los valores de "estado" de la tabla
            String estadoValue = totalTiposVehiculos.getValueAt(fila, 2).toString();
            // Seleccionar los valores correspondientes en los JComboBox
            estado.setSelectedItem(estadoValue);
            
            try {
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error al seleccionar, error: "+e.toString());
            }
        }
    }    
}

