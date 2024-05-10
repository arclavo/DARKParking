package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Usuarios;
import modelo.ConsultasUsuarios;
import vista.frmUsuarios;
/**
 * @author DChangC
 */
public class CtrlUsuarios implements ActionListener {  
    /*    
     ***************************************************************************
     * Variables de instancia que representan:                                 *
     * - Modelo de datos                                                       *
     * - Consultas a la BD                                                     *
     * - Consultas a la Vista                                                  *
     * Son marcadas como "final" porque una vez inicializadas en el            *
     * constructor no pueden ser modificadas.                                  *
     ***************************************************************************
    */
    
    private final Usuarios modelo;     //Clase que modela la estructura de un Usuario
    private final ConsultasUsuarios consultas;    //Clase que proporciona métodos para interactuar con la BD
    private final frmUsuarios vista;   //Clase que representa la interfaz de usuario para la gestión de Usuarioes
    
    //Constructor
    //Toma 3 parámetros (modelo, consultas y vista) que son objetos de la clase
    //"Usuarios", "ConsultasUsuarios" y "frmUsuarios"
    public CtrlUsuarios(Usuarios modelo, ConsultasUsuarios consultas, frmUsuarios vista){           
        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
     // this.vista.btnBuscar.addActionListener(this);
    }
    
    //Método para inicializar la vista
    public void iniciar() {
        vista.setTitle("Usuarios");
        vista.setLocationRelativeTo(null);
        vista.txtId_Usuario.setVisible(true);
    }
    
    /*
     ***************************************************************************  
     * Método parte de la interfaz "ActionListener", se ejecutará cada vez que *
     * se produzca una acción (presionar un botón).  El parámetro "e" es un    *
     * objeto de tipo "ActionEvent", que contiene información de la acción.    *
     * Anotación "@Override", indica que un método está siendo sobreescrito de *
     * una clase padre. Ayuda a prevenir errores (cuando método no existe en   *
     * la clase padre, se genera un error.)                                    *
     ***************************************************************************
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Manejo de eventos de los botones del formulario
        //Botón: GUARDAR
        if (e.getSource() == vista.btnGuardar) {
            // Verifica si algún campo está vacío
            if (vista.txtApellidos.getText().isEmpty() ||
                vista.txtNombres.getText().isEmpty() ||
                vista.txtTelefono.getText().isEmpty() ||
                vista.txtEmail.getText().isEmpty() ||
                vista.txtPassword.getText().isEmpty() ||
                vista.cmbRol.getSelectedItem() == null ||
                vista.cmbEstado.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Existen campos vacíos, favor completarlos.");
                return; // Sale del método actionPerformed sin continuar
            }

            // Verifica si el código del Usuario ya existe en la base de datos
            /*
            if (consultas.existeCodigo(vista.txtCodigo.getText())) {
                JOptionPane.showMessageDialog(null, "El código de Usuario ya existe. No se puede guardar.");
                limpiar();
                return; // Sale del método actionPerformed sin continuar
            }
            */

            //Obtiene datos de la vista y los asigna al modelo
         // modelo.setCodigo(vista.txtCodigo.getText());
            modelo.setApellidos(vista.txtApellidos.getText());
            modelo.setNombres(vista.txtNombres.getText());
            modelo.setTelefono(vista.txtTelefono.getText());
            modelo.setEmail(vista.txtEmail.getText());
            modelo.setPassword(vista.txtPassword.getText());
            modelo.setRol((String) vista.cmbRol.getSelectedItem());
            modelo.setEstado((String) vista.cmbEstado.getSelectedItem());

          //modelo.setEstado(vista.txtEstado.getText());    // Campo jText eliminado del formulario
            
            // Registra el Usuario y muestra un mensaje de éxito o error
            if (consultas.registrar(modelo)) {
                JOptionPane.showMessageDialog(null, "Usuario se guardó correctamente.");
                limpiar();
                // ACTUALIZA TABLA DEL FORMULARIO
                try {
                    consultas.MostrarUsuarios(vista.tbUsuarios); // vista.tbUsuarios es la tabla donde se mostrarán los usuarios
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al mostrar usuarios: " + ex.getMessage());
                }                
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar el Usuario.");
                limpiar();
            }
        }    
        
        //Botón: MODIFICAR
        if (e.getSource() == vista.btnModificar) {
            // Obtiene datos de la vista y los asigna al modelo
            // Valida que campo Id no se encuentre vacía
            if (!vista.txtId_Usuario.getText().isEmpty()) {
                modelo.setId_Usuario(Integer.parseInt(vista.txtId_Usuario.getText()));
             // modelo.setCodigo(vista.txtCodigo.getText());
                modelo.setApellidos(vista.txtApellidos.getText());
                modelo.setNombres(vista.txtNombres.getText());
                modelo.setTelefono(vista.txtTelefono.getText());
                modelo.setEmail(vista.txtEmail.getText());
                modelo.setPassword(vista.txtPassword.getText());
                modelo.setRol((String) vista.cmbRol.getSelectedItem());
                modelo.setEstado((String) vista.cmbEstado.getSelectedItem());
            
                // Modifica el Usuario y muestra un mensaje de éxito o error
                if (consultas.modificar(modelo)) {
                    JOptionPane.showMessageDialog(null, "Usuario se actualizó correctamente.");
                    limpiar();
                    // ACTUALIZA TABLA DEL FORMULARIO                
                    try {
                        consultas.MostrarUsuarios(vista.tbUsuarios); // vista.tbUsuarios es la tabla donde se mostrarán los usuarios
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al mostrar usuarios: " + ex.getMessage());
                    }                    
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el Usuario.");
                    limpiar();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un Usuario de la lista.");
                vista.txtId_Usuario.requestFocus(); // Retorna el foco al campo Id
            }            
        }
        
        //Botón: ELIMINAR
        if (e.getSource() == vista.btnEliminar) {
            // Obtiene el ID de la vista y lo asigna al modelo
            // Valida que campo Id no se encuentre vacía
            if (!vista.txtId_Usuario.getText().isEmpty()) {
                modelo.setId_Usuario(Integer.parseInt(vista.txtId_Usuario.getText()));
            
                // Elimina el Usuario y muestra un mensaje de éxito o error
                if (consultas.eliminar(modelo)) {
                    JOptionPane.showMessageDialog(null, "Usuario se eliminó correctamente.");
                    limpiar();
                    // ACTUALIZA TABLA DEL FORMULARIO                
                    try {
                        consultas.MostrarUsuarios(vista.tbUsuarios); // vista.tbUsuarios es la tabla donde se mostrarán los usuarios
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al mostrar usuarios: " + ex.getMessage());
                    }                    
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el Usuario.");
                    limpiar();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un Usuario de la lista.");
                vista.txtId_Usuario.requestFocus(); // Retorna el foco al campo Id
            }                  
        }        
        
        /*
        //Botón: BUSCAR
        if (e.getSource() == vista.btnBuscar) {
            // Obtiene el código de la vista y lo asigna al modelo
            modelo.setCodigo(vista.txtCodigo.getText());
            
            // Busca el Usuario y muestra sus datos o un mensaje de Usuario no encontrado
            if (consultas.buscar(modelo)) {
                vista.txtId_Usuario.setText(String.valueOf(modelo.getId_Usuario()));
                vista.txtCodigo.setText(modelo.getCodigo());
                vista.txtApellidos.setText(modelo.getApellidos());
                vista.txtNombres.setText(modelo.getNombres());
                vista.txtTelefono.setText(modelo.getTelefono());
                vista.txtEmail.setText(modelo.getEmail());
                vista.txtPassword.setText(modelo.getPassword());
                vista.cmbRol.setSelectedItem(modelo.getRol());
                vista.cmbEstado.setSelectedItem(modelo.getEstado());
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
                limpiar();
            }
        }
        */
    
        //Botón: LIMPIAR
        if (e.getSource() == vista.btnLimpiar) {
            // Limpia los campos de la vista
            limpiar();
        }
    }

    // Método para limpiar los campos de la vista
    public void limpiar() {
        vista.txtId_Usuario.setText(null);
     // vista.txtCodigo.setText(null);
        vista.txtApellidos.setText(null);
        vista.txtNombres.setText(null);
        vista.txtTelefono.setText(null);
        vista.txtEmail.setText(null);
        vista.txtPassword.setText(null);
        vista.cmbRol.setSelectedIndex(0);    // Indice comienza desde 0 (0=ADMINISTRADOR, 1=EMPLEADO)
        vista.cmbEstado.setSelectedIndex(0); // Indice comienza desde 0 (0=ACTIVO, 1=INACTIVO)
    }
}    
