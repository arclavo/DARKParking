/*
 *******************************************************************************
 * Clase "ConsultasUsuarios" realiza operaciones de consulta, inserción,       *
 * modificación y eliminación en la tabla de la BD.                            *
 *******************************************************************************
*/
package modelo;

// Importaciones para trabajar con componentes gráficos, fuentes, tablas y bases de datos
import java.awt.Font; // Fuentes
import java.awt.Image; // Imágenes
import javax.swing.ImageIcon; // Iconos de imagen
import javax.swing.JTable; // Tablas
import javax.swing.table.DefaultTableModel; // Uso de modelo predeterminado de tabla
import javax.swing.table.DefaultTableCellRenderer; // Renderizar celdas de tabla
import javax.swing.table.JTableHeader; // Encabezados de tabla
import javax.swing.table.TableColumnModel; // Modelo de columnas de tabla
import java.sql.Connection; // Conexiones a la base de datos
import java.sql.PreparedStatement; // Preparar consultas SQL
import java.sql.ResultSet; // Almacenar resultados de consultas SQL
import java.sql.SQLException; // Excepciones de SQL
import java.sql.Statement; // Ejecutar consultas SQL
import java.text.SimpleDateFormat; // Formatear fechas
import java.util.Date;
import javax.swing.JOptionPane; // Mensajes emergentes
import java.util.logging.Level; // Niveles de registro de mensajes
import java.util.logging.Logger; // Registrar mensajes de registro
import javax.swing.JComboBox;
import javax.swing.JLabel; // Etiquetas de texto
import javax.swing.JTextField;

/*
 *******************************************************************************
 * Clase extiende la clase "Conexion" (hereda todas las funcionalidades y      *
 * propiedades de la clase "Conexion" ("ConsultasUsuarios es la clase hija y   *
 * "Conexion" es la clase padre).                                              *
 *******************************************************************************
*/
public class ConsultasUsuarios extends Conexion{
    
    int idUsuario;

    // Constructor
    public void establecerIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    // LLENA COMBO USUARIO
    /*
    public void MostrarUsuariosCombo(JComboBox comboUsuario) throws SQLException {
        
     // modelo.Conexion objetoConexion = new modelo.Conexion();
        
        // Inicializa variables
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Connection con = getConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();        
        
        String sql = "SELECT * FROM USUARIOS WHERE estado='ACTIVO'";
        
      //Statement st;
        
        try{
         // st = objetoConexion.getConexion().createStatement();
            
            ps = con.prepareStatement(sql); // Prepara la consulta sql
            rs = ps.executeQuery();  // Ejecuta la consulta en la BD
            
            
         // ResultSet rs = st.executeQuery(sql);
            comboUsuario.removeAllItems();
         
            while (rs.next()) {
                String nombreUsuario = rs.getString("nombres");
                this.establecerIdUsuario(rs.getInt("id_usuario"));
                    
                comboUsuario.addItem(nombreUsuario);
                comboUsuario.putClientProperty(nombreUsuario,idUsuario);
            } 

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al mostrar Usuario"+e.toString());
        }
        
        finally {
            con.close();
        }
    }    
    */
    
    public void MostrarUsuariosCombo(JComboBox comboUsuario) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConexion();
            String sql = "SELECT * FROM USUARIOS WHERE estado='ACTIVO'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            comboUsuario.removeAllItems();

            while (rs.next()) {
                String nombreUsuario = rs.getString("nombres");
                this.establecerIdUsuario(rs.getInt("id_usuario"));
                
                comboUsuario.addItem(nombreUsuario);
                comboUsuario.putClientProperty(nombreUsuario,idUsuario);
            } 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al mostrar Usuarios: " + e.getMessage());
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
    
    // GRABA NUEVO REGISTRO EN LA TABLA DE LA BD
    public boolean registrar(Usuarios usu) {
        // Inicializa variables
        PreparedStatement ps = null;
        Connection con = getConexion();

     // String sql = "INSERT INTO usuarios (codigo, apellidos, nombres, telefono, email, password, rol, estado) VALUES(?,?,?,?,?,?,?,?)";
        String sql = "INSERT INTO usuarios (apellidos, nombres, telefono, email, password, rol, estado) VALUES(?,?,?,?,?,?,?)";

        // Manejo de excepciones
        try {
            ps = con.prepareStatement(sql);  // Prepara la consulta

            // Establece valores de parámetros en una consulta preparada
         // ps.setString(1, usu.getCodigo());
            ps.setString(1, usu.getApellidos());
            ps.setString(2, usu.getNombres());
            ps.setString(3, usu.getTelefono());
            ps.setString(4, usu.getEmail());
            ps.setString(5, usu.getPassword());
            ps.setString(6, usu.getRol());
            ps.setString(7, usu.getEstado()); 
            
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
    public boolean modificar(Usuarios usu) {
        // Inicializa variables
        PreparedStatement ps = null;
        Connection con = getConexion();

     // String sql = "UPDATE usuarios SET codigo=?, apellidos=?, nombres=?, telefono=?, email=?, password=?, rol=?, estado=? WHERE id_Usuario=?";
        String sql = "UPDATE usuarios SET apellidos=?, nombres=?, telefono=?, email=?, password=?, rol=?, estado=? WHERE id_Usuario=?";

        // Manejo de excepciones
        try {
            ps = con.prepareStatement(sql);

            // Establece valores de parámetros en una consulta preparada
         // ps.setString(1, usu.getCodigo());
            ps.setString(1, usu.getApellidos());
            ps.setString(2, usu.getNombres());
            ps.setString(3, usu.getTelefono());
            ps.setString(4, usu.getEmail());
            ps.setString(5, usu.getPassword());
            ps.setString(6, usu.getRol());
            ps.setString(7, usu.getEstado()); 
            ps.setInt(8, usu.getId_Usuario());            
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
    public boolean eliminar(Usuarios usu) {
        // Inicializa variables
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM usuarios WHERE id_Usuario=?";

        // Manejo de excepciones
        try {
            ps = con.prepareStatement(sql);
            
            // Establece valores de parámetros en una consulta preparada
            ps.setInt(1, usu.getId_Usuario());
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
    /*
    public boolean buscar(Usuarios usu) {
        // Inicializa variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM usuarios WHERE codigo=?";

        // Manejo de excepciones
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usu.getCodigo());
            rs = ps.executeQuery();

            if (rs.next()) {
                // Establece valores de parámetros en una consulta preparada
                usu.setId_Usuario(Integer.parseInt(rs.getString("id_Usuario")));
                usu.setCodigo(rs.getString("codigo"));
                usu.setApellidos(rs.getString("apellidos"));
                usu.setNombres(rs.getString("nombres"));
                usu.setTelefono(rs.getString("telefono"));
                usu.setEmail(rs.getString("email"));
                usu.setPassword(rs.getString("password"));
                usu.setRol(rs.getString("rol"));
                usu.setEstado(rs.getString("estado"));                
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
    // Verifica si existe un Cliente con el mismo código en la BD
    public boolean existeCodigo(String codigo) {
        // Inicializa variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT COUNT(*) AS count FROM usuarios WHERE codigo=?";

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
    public void MostrarUsuarios(JTable tbUsuarios) throws SQLException {
       
        // Inicializa variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        
     // String sql = "SELECT id_usuario,codigo,apellidos,nombres,telefono,email,password,rol,estado FROM usuarios";
        String sql = "SELECT id_usuario,apellidos,nombres,telefono,email,password,rol,estado FROM usuarios";
        
        // Creo cabeceras de columnas de la Tabla en el formulario
        modelo.addColumn("Id");
     // modelo.addColumn("Codigo");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Nombres");
        modelo.addColumn("Telefono");
        modelo.addColumn("Email");
        modelo.addColumn("Password");
        modelo.addColumn("Rol");
        modelo.addColumn("Estado");
        
        tbUsuarios.setModel(modelo);
        
        // Cambiar tamaño de fuente y tipo de fuente para la tabla
        tbUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 9));
        
        // Cambiar tamaño de fuente y tipo de fuente para los títulos de las columnas
        JTableHeader header = tbUsuarios.getTableHeader();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tbUsuarios.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER); // Centra el texto de los títulos
        Font headerFont = new Font("Tahoma", Font.BOLD, 9); // Por ejemplo, tamaño 12 en negrita
        header.setFont(headerFont);

        // Cambiar tamaño de columna
        TableColumnModel columnModel = tbUsuarios.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(10);  //Id
     // columnModel.getColumn(1).setPreferredWidth(40);  //Código
        columnModel.getColumn(1).setPreferredWidth(100); //Apellidos
        columnModel.getColumn(2).setPreferredWidth(100); //Nombres
        columnModel.getColumn(3).setPreferredWidth(40);  //Telefono
        columnModel.getColumn(4).setPreferredWidth(100); //Email
        columnModel.getColumn(5).setPreferredWidth(20);  //Password
        columnModel.getColumn(6).setPreferredWidth(30);  //Rol
        columnModel.getColumn(7).setPreferredWidth(20);  //Estado
        
        try {
            ps = con.prepareStatement(sql); // Prepara la consulta sql
            rs = ps.executeQuery();  // Ejecuta la consulta en la BD
            
            while (rs.next()) {
                String id_usuario = rs.getString("id_usuario");
             // String codigo = rs.getString("codigo");
                String apellidos = rs.getString("apellidos");                
                String nombres = rs.getString("nombres");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");                
                String password = rs.getString("password");
                String rol = rs.getString("rol");
                String estado = rs.getString("estado");
                
             // modelo.addRow(new Object[]{id_usuario, codigo, apellidos, nombres, telefono, email, password, rol, estado});
                modelo.addRow(new Object[]{id_usuario, apellidos, nombres, telefono, email, password, rol, estado});
               
             // tbUsuarios.setModel(modelo);
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
 // public void Seleccionar(JTable totalUsuarios, JTextField id, JTextField codigo, JTextField apellidos, JTextField nombres, JTextField telefono, JTextField email, JTextField password, JComboBox rol, JComboBox estado) {
    public void Seleccionar(JTable totalUsuarios, JTextField id, JTextField apellidos, JTextField nombres, JTextField telefono, JTextField email, JTextField password, JComboBox rol, JComboBox estado) {        
 
    
        int fila = totalUsuarios.getSelectedRow();
        
        if (fila >= 0) {
            id.setText(totalUsuarios.getValueAt(fila,0).toString());
         // codigo.setText(totalUsuarios.getValueAt(fila,1).toString());
            apellidos.setText(totalUsuarios.getValueAt(fila,1).toString());
            nombres.setText(totalUsuarios.getValueAt(fila,2).toString());
            telefono.setText(totalUsuarios.getValueAt(fila,3).toString());
            email.setText(totalUsuarios.getValueAt(fila,4).toString());
            password.setText(totalUsuarios.getValueAt(fila,5).toString());
            
            // Obtener los valores de rol y estado de la tabla
            String rolValue = totalUsuarios.getValueAt(fila, 6).toString();
            String estadoValue = totalUsuarios.getValueAt(fila, 7).toString();
        
            // Seleccionar los valores correspondientes en los JComboBox
            rol.setSelectedItem(rolValue);
            estado.setSelectedItem(estadoValue);
            
            
            /*
            rol.setText(totalUsuarios.getValueAt(fila,7).toString());
            estado.setText(totalUsuarios.getValueAt(fila,8).toString());
            */
            /*            
            rol.setSelectedItem(totalUsuarios.getValueAt(fila,7).toString());
            estado.setSelectedItem(totalUsuarios.getValueAt(fila,8).toString());
            */

            try {
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error al seleccionar, error: "+e.toString());
            }
        }
    }
    
    /*
    *******************************************************************************************************
    SELECCIONA REGISTRO DE LA TABLA Y MUESTRA EN LOS CAMPOS (CON VALIDACIONES DE NULL EN TEXTOS Y COMBOS
    *******************************************************************************************************
    public void Seleccionar(JTable totalUsuarios, JTextField id, JTextField apellidos, JTextField nombres, JTextField telefono, JTextField email, JTextField password, JComboBox rol, JComboBox estado) {
    
        int fila = totalUsuarios.getSelectedRow();
        
        if (fila >= 0) {
            id.setText(getValueAsString(totalUsuarios, fila, 0));
            apellidos.setText(getValueAsString(totalUsuarios, fila, 1));
            nombres.setText(getValueAsString(totalUsuarios, fila, 2));
            telefono.setText(getValueAsString(totalUsuarios, fila, 3));
            email.setText(getValueAsString(totalUsuarios, fila, 4));
            password.setText(getValueAsString(totalUsuarios, fila, 5));
            setSelectedItemSafe(rol, totalUsuarios.getValueAt(fila, 6));
            setSelectedItemSafe(estado, totalUsuarios.getValueAt(fila, 7));
        }
    }

    private String getValueAsString(JTable table, int row, int column) {
        Object value = table.getValueAt(row, column);
        return (value != null) ? value.toString() : "";
    }

    private void setSelectedItemSafe(JComboBox comboBox, Object item) {
        if (item != null) {
            comboBox.setSelectedItem(item.toString());
        }
    }
    */
}

