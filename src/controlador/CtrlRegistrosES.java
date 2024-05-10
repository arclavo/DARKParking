package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Clientes;
import modelo.ConsultasClientes;
import vista.frmRegistrosES;
/**
 * @author DChangC
 */
public class CtrlRegistrosES implements ActionListener {  
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
    
    private final Clientes modelo;     //Clase que modela la estructura de un Cliente
    private final ConsultasClientes consultas;    //Clase que proporciona métodos para interactuar con la BD
    private final frmRegistrosES vista;   //Clase que representa la interfaz de usuario para la gestión de Clientees
    
    //Constructor
    //Toma 3 parámetros (modelo, consultas y vista) que son objetos de la clase
    //"Clientes", "ConsultasClientes" y "frmClientes"
    public CtrlRegistrosES(Clientes modelo, ConsultasClientes consultas, frmRegistrosES vista){           
        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
    }
    
    //Método para inicializar la vista
    public void iniciar() {
        vista.setTitle("Clientes");
        vista.setLocationRelativeTo(null);
        vista.txtId_Cliente.setVisible(true);
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
        /*
        if (e.getSource() == vista.btnGuardar) {
            // Verifica si algún campo está vacío
            if (vista.txtCodigo.getText().isEmpty() ||
                vista.txtApellidos.getText().isEmpty() ||
                vista.txtNombres.getText().isEmpty() ||
                vista.txtDireccion.getText().isEmpty() ||                    
                vista.txtTelefono.getText().isEmpty() ||
                vista.txtCi_Ruc.getText().isEmpty() ||                    
                vista.txtEmail.getText().isEmpty() ||
                vista.cmbEstado.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Existen campos vacíos, favor completarlos.");
                return; // Sale del método actionPerformed sin continuar
            }            
            // Verifica si el código del Cliente ya existe en la base de datos
            if (consultas.existeCodigo(vista.txtCodigo.getText())) {
                JOptionPane.showMessageDialog(null, "El código de Cliente ya existe. No se puede guardar.");
                limpiar();
                return; // Sale del método actionPerformed sin continuar
            }
            
            //Obtiene datos de la vista y los asigna al modelo
            modelo.setCodigo(vista.txtCodigo.getText());
            modelo.setApellidos(vista.txtApellidos.getText());
            modelo.setNombres(vista.txtNombres.getText());
            modelo.setDireccion(vista.txtDireccion.getText());
            modelo.setTelefono(vista.txtTelefono.getText());
            modelo.setCi_Ruc(vista.txtCi_Ruc.getText());
            modelo.setEmail(vista.txtEmail.getText());
          //modelo.setEstado(vista.cmbEstado.getText());
            modelo.setEstado((String) vista.cmbEstado.getSelectedItem());

          //modelo.setEstado(vista.txtEstado.getText());    // Campo jText eliminado del formulario
            
            // Registra el Cliente y muestra un mensaje de éxito o error
            if (consultas.registrar(modelo)) {
                JOptionPane.showMessageDialog(null, "Cliente se guardó correctamente.");
                limpiar();
                // ACTUALIZA TABLA DEL FORMULARIO                
                try {
                    consultas.MostrarClientes(vista.tbClientes); 
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al mostrar clientes. Error: " + ex.getMessage());
                }                    
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar el Cliente.");
                limpiar();
            }
        }    
        
        //Botón: MODIFICAR
        if (e.getSource() == vista.btnModificar) {
            // Valida que campo Id no se encuentre vacía
            if (!vista.txtId_Cliente.getText().isEmpty()) {            
                // Obtiene datos de la vista y los asigna al modelo
                modelo.setId_Cliente(Integer.parseInt(vista.txtId_Cliente.getText()));
                modelo.setCodigo(vista.txtCodigo.getText());
                modelo.setApellidos(vista.txtApellidos.getText());
                modelo.setNombres(vista.txtNombres.getText());
                modelo.setDireccion(vista.txtDireccion.getText());
                modelo.setTelefono(vista.txtTelefono.getText());
                modelo.setCi_Ruc(vista.txtCi_Ruc.getText());
                modelo.setEmail(vista.txtEmail.getText());
            // modelo.setEstado(vista.txtEstado.getText());     // Campo jText eliminado del formulario
                modelo.setEstado((String) vista.cmbEstado.getSelectedItem());
            
                // Modifica el Cliente y muestra un mensaje de éxito o error
                if (consultas.modificar(modelo)) {
                    JOptionPane.showMessageDialog(null, "Cliente se actualizó correctamente.");
                    limpiar();
                    // ACTUALIZA TABLA DEL FORMULARIO                
                    try {
                        consultas.MostrarClientes(vista.tbClientes); 
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al mostrar clientes. Error: " + ex.getMessage());
                    }                    
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el Cliente.");
                    limpiar();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un Usuario de la lista.");
                vista.txtId_Cliente.requestFocus(); // Retorna el foco al campo Id
            }                 
        }
        
        //Botón: ELIMINAR
        if (e.getSource() == vista.btnEliminar) {
            // Valida que campo Id no se encuentre vacía
            if (!vista.txtId_Cliente.getText().isEmpty()) {            
                // Obtiene el ID de la vista y lo asigna al modelo
                modelo.setId_Cliente(Integer.parseInt(vista.txtId_Cliente.getText()));
            
                // Elimina el Cliente y muestra un mensaje de éxito o error
                if (consultas.eliminar(modelo)) {
                    JOptionPane.showMessageDialog(null, "Cliente se eliminó correctamente.");
                    limpiar();
                    // ACTUALIZA TABLA DEL FORMULARIO                
                    try {
                        consultas.MostrarClientes(vista.tbClientes); 
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al mostrar clientes. Error: " + ex.getMessage());
                    }                    
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el Cliente.");
                    limpiar();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un Usuario de la lista.");
                vista.txtId_Cliente.requestFocus(); // Retorna el foco al campo Id
            }                 
        }        
        */    

        //Botón: BUSCAR
        if (e.getSource() == vista.btnBuscar) {
            // Obtiene el código de la vista y lo asigna al modelo
            modelo.setCi_Ruc(vista.txtCiRucCliente.getText());
            
            // Busca el Cliente y muestra sus datos o un mensaje de Cliente no encontrado
            if (consultas.buscar(modelo)) {
                vista.txtId_Cliente.setText(String.valueOf(modelo.getId_Cliente()));
             // vista.txtCodigo.setText(modelo.getCodigo());
                vista.txtNombreCliente.setText(modelo.getApellidos());
             // vista.txtApellidos.setText(modelo.getApellidos());
             // vista.txtNombres.setText(modelo.getNombres());
             // vista.txtDireccion.setText(modelo.getDireccion());
             // vista.txtTelefono.setText(modelo.getTelefono());
             // vista.txtCi_Ruc.setText(modelo.getCi_Ruc());
                vista.txtEmailCliente.setText(modelo.getEmail());                
             // vista.cmbEstado.setSelectedItem(modelo.getEstado());
            } else {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                limpiar();
            }
        }
    
        //Botón: LIMPIAR
        if (e.getSource() == vista.btnLimpiar) {
            // Limpia los campos de la vista
            limpiar();
        }
    }

    // Método para limpiar los campos de la vista
    public void limpiar() {
        vista.txtId_Cliente.setText(null);
     // vista.txtCodigo.setText(null);
        vista.txtNombreCliente.setText(null);
     // vista.txtApellidos.setText(null);
     // vista.txtNombres.setText(null);
     // vista.txtDireccion.setText(null);
     // vista.txtTelefono.setText(null);
        vista.txtCiRucCliente.setText(null);
        vista.txtEmailCliente.setText(null);
     // vista.cmbEstado.setSelectedIndex(0); // Indice comienza desde 0 (0=ACTIVO, 1=INACTIVO)
    }
}    
