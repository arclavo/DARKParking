/*
 *******************************************************************************
 * Clase "ConsultasClientes" realiza operaciones de consulta, inserción,       *
 * modificación y eliminación en la tabla de la BD.                            *
 *******************************************************************************
*/
package modelo;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/*
 *******************************************************************************
 * Clase extiende la clase "Conexion" (hereda todas las funcionalidades y      *
 * propiedades de la clase "Conexion" ("ConsultasClientes es la clase hija y   *
 * "Conexion" es la clase padre).                                              *
 *******************************************************************************
*/
public class ConsultasClientes extends Conexion{
    int idCliente;

    // Constructor
    public void establecerIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    // LLENA COMBO
    public void MostrarClientesCombo(JComboBox comboCliente) throws SQLException {
        
        // Inicializa variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();        
        
        String sql = "SELECT * FROM CLIENTES WHERE estado='ACTIVO'";
        
      //Statement st;
        
        try{
         // st = objetoConexion.getConexion().createStatement();
            
            ps = con.prepareStatement(sql); // Prepara la consulta sql
            rs = ps.executeQuery();  // Ejecuta la consulta en la BD
            
            
         // ResultSet rs = st.executeQuery(sql);
            comboCliente.removeAllItems();
         
            while (rs.next()) {
                String nombreCliente = rs.getString("nombres");
                this.establecerIdCliente(rs.getInt("id_cliente"));
                    
                comboCliente.addItem(nombreCliente);
                comboCliente.putClientProperty(nombreCliente,idCliente);
            } 

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al mostrar Cliente. Error: "+e.toString());
        }
        
        finally {
            con.close();
        }
    }    
        
    // Registra un cliente nuevo en la BD
    public boolean registrar(Clientes cli) {
        // Inicializa variables
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO clientes (codigo, apellidos, nombres, direccion, telefono, ci_ruc, email, estado) VALUES(?,?,?,?,?,?,?,?)";

        // Manejo de excepciones
        try {
            ps = con.prepareStatement(sql);  // Prepara la consulta

            // Establece valores de parámetros en una consulta preparada
            ps.setString(1, cli.getCodigo());
            ps.setString(2, cli.getApellidos());
            ps.setString(3, cli.getNombres());
            ps.setString(4, cli.getDireccion());
            ps.setString(5, cli.getTelefono());
            ps.setString(6, cli.getCi_Ruc());            
            ps.setString(7, cli.getEmail());            
            ps.setString(8, cli.getEstado()); 
            
            //txtEstado.setText(cmbEstado.getSelectedItem().toString());    //Campo del formulario eliminado
            
            
            // "cmbEstado" es el nombre del ComboBox
            // String estadoSeleccionado = (String) cmbEstado.getSelectedItem();
            // ps.setString(8, estadoSeleccionado);            
            
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

    // Modifica un Cliente existente en la BD
    public boolean modificar(Clientes cli) {
        // Inicializa variables
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE clientes SET codigo=?, apellidos=?, nombres=?, direccion=?, telefono=?, ci_ruc=?, email=?, estado=? WHERE id_Cliente=?";

        // Manejo de excepciones
        try {
            ps = con.prepareStatement(sql);

            // Establece valores de parámetros en una consulta preparada
            ps.setString(1, cli.getCodigo());
            ps.setString(2, cli.getApellidos());
            ps.setString(3, cli.getNombres());
            ps.setString(4, cli.getDireccion());
            ps.setString(5, cli.getTelefono());
            ps.setString(6, cli.getCi_Ruc());
            ps.setString(7, cli.getEmail());
            ps.setString(8, cli.getEstado());
            ps.setInt(9, cli.getId_Cliente());
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

    // Elimina un Cliente existente en la BD
    public boolean eliminar(Clientes cli) {
        // Inicializa variables
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM clientes WHERE id_Cliente=?";

        // Manejo de excepciones
        try {
            ps = con.prepareStatement(sql);
            
            // Establece valores de parámetros en una consulta preparada
            ps.setInt(1, cli.getId_Cliente());
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

    // Busca un Cliente en la BD por su código
    public boolean buscar(Clientes cli) {
        // Inicializa variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM clientes WHERE codigo=?";

        // Manejo de excepciones
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getCodigo());
            rs = ps.executeQuery();

            if (rs.next()) {
                // Establece valores de parámetros en una consulta preparada
                cli.setId_Cliente(Integer.parseInt(rs.getString("id_cliente")));
                cli.setCodigo(rs.getString("codigo"));
                cli.setApellidos(rs.getString("apellidos"));
                cli.setNombres(rs.getString("nombres"));
                cli.setDireccion(rs.getString("direccion"));
                cli.setTelefono(rs.getString("telefono"));
                cli.setCi_Ruc(rs.getString("ci_ruc"));
                cli.setEmail(rs.getString("email"));
                cli.setEstado(rs.getString("estado"));                
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
    
    // Verifica si existe un Cliente con el mismo código en la BD
    public boolean existeCodigo(String codigo) {
        // Inicializa variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT COUNT(*) AS count FROM clientes WHERE codigo=?";

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
    
    // Muestra "todos" los usuarios de la tabla en el formulario (JTable)
    public void MostrarClientes(JTable tbClientes) throws SQLException {
       
        // Inicializa variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        
        String sql = "SELECT id_cliente,codigo,apellidos,nombres,direccion,telefono,ci_ruc,email,estado FROM clientes";
        
        // Creo cabeceras de columnas de la Tabla en el formulario
        modelo.addColumn("Id");
        modelo.addColumn("Codigo");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Nombres");
        modelo.addColumn("Dirección");
        modelo.addColumn("Telefono");
        modelo.addColumn("Ci_Ruc");
        modelo.addColumn("Email");
        modelo.addColumn("Estado");
        
        tbClientes.setModel(modelo);
        
        // Cambiar tamaño de fuente y tipo de fuente para la tabla
        tbClientes.setFont(new Font("Tahoma", Font.PLAIN, 9));
        
        // Cambiar tamaño de fuente y tipo de fuente para los títulos de las columnas
        JTableHeader header = tbClientes.getTableHeader();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tbClientes.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER); // Centra el texto de los títulos
        Font headerFont = new Font("Tahoma", Font.BOLD, 9); // Por ejemplo, tamaño 12 en negrita
        header.setFont(headerFont);

        // Cambiar tamaño de columna
        TableColumnModel columnModel = tbClientes.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(10);  //Id
        columnModel.getColumn(1).setPreferredWidth(60);  //Código
        columnModel.getColumn(2).setPreferredWidth(100); //Apellidos
        columnModel.getColumn(3).setPreferredWidth(100); //Nombres
        columnModel.getColumn(4).setPreferredWidth(80);  //Dirección
        columnModel.getColumn(5).setPreferredWidth(40); //Telefono
        columnModel.getColumn(6).setPreferredWidth(60);  //Ci_Ruc
        columnModel.getColumn(7).setPreferredWidth(80);  //Email
        columnModel.getColumn(8).setPreferredWidth(30);  //Estado
        
        try {
            ps = con.prepareStatement(sql); // Prepara la consulta sql
            rs = ps.executeQuery();  // Ejecuta la consulta en la BD
            
            while (rs.next()) {
                String id_cliente = rs.getString("id_cliente");
                String codigo = rs.getString("codigo");
                String apellidos = rs.getString("apellidos");                
                String nombres = rs.getString("nombres");
                String direccion = rs.getString("direccion");                
                String telefono = rs.getString("telefono");
                String ci_ruc = rs.getString("ci_ruc");
                String email = rs.getString("email");                
                String estado = rs.getString("estado");
                
                modelo.addRow(new Object[]{id_cliente, codigo, apellidos, nombres, direccion, telefono, ci_ruc, email, estado});
               
             // tbClientes.setModel(modelo);
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
    
    // SELECCIONA CON CLIC DEL MOUSE EL REGISTRO DE LA TABLA EN FORMULARIO Y MUESTRA EN LOS CAMPOS
    public void Seleccionar(JTable totalClientes, JTextField id, JTextField codigo, JTextField apellidos, JTextField nombres, JTextField direccion, JTextField telefono, JTextField ci_ruc, JTextField email, JComboBox estado) {
    
        int fila = totalClientes.getSelectedRow();
        
        if (fila >= 0) {
            id.setText(totalClientes.getValueAt(fila,0).toString());
            codigo.setText(totalClientes.getValueAt(fila,1).toString());
            apellidos.setText(totalClientes.getValueAt(fila,2).toString());
            nombres.setText(totalClientes.getValueAt(fila,3).toString());
            direccion.setText(totalClientes.getValueAt(fila,4).toString());
            telefono.setText(totalClientes.getValueAt(fila,5).toString());
            ci_ruc.setText(totalClientes.getValueAt(fila,6).toString());
            email.setText(totalClientes.getValueAt(fila,7).toString());
            
            // Obtener los valores de estado de la tabla
            String estadoValue = totalClientes.getValueAt(fila, 8).toString();
        
            // Seleccionar los valores correspondientes en los JComboBox
            estado.setSelectedItem(estadoValue);
            
            /*
            rol.setText(totalClientes.getValueAt(fila,7).toString());
            estado.setText(totalClientes.getValueAt(fila,8).toString());
            */
            /*            
            rol.setSelectedItem(totalClientes.getValueAt(fila,7).toString());
            estado.setSelectedItem(totalClientes.getValueAt(fila,8).toString());
            */

            try {
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error al seleccionar, error: "+e.toString());
            }
        }
    }
}

