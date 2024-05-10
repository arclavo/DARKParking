
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.TiposVehiculos;
import modelo.ConsultasTiposVehiculos;
import vista.frmTiposVehiculos;

/**
 * @author DChangC
 */
public class CtrlTiposVehiculos implements ActionListener {  
    
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
    
    private final TiposVehiculos modelo;                //Clase que modela la estructura
    private final ConsultasTiposVehiculos consultas;    //Clase que proporciona métodos para interactuar con la BD
    private final frmTiposVehiculos vista;              //Clase que representa la interfaz de usuario
    
    //Constructor
    //Toma 3 parámetros (modelo, consultas y vista) que son objetos de la clase
    //"TiposVehiculos", "ConsultasTiposVehiculos" y "frmTiposVehiculos"
    public CtrlTiposVehiculos(TiposVehiculos modelo, ConsultasTiposVehiculos consultas, frmTiposVehiculos vista){           
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
        vista.setTitle("Tipos de Vehiculos");
        vista.setLocationRelativeTo(null);
        vista.txtId_TipoVehiculo.setVisible(true);
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
            if (vista.txtDescripcion.getText().isEmpty() ||
                vista.cmbEstado.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Existen campos vacíos, favor completarlos.");
                return; // Sale del método actionPerformed sin continuar
            }            

            // Verifica si el código ya existe en la base de datos
            /*
            if (consultas.existeCodigo(vista.txtCodigo.getText())) {
                JOptionPane.showMessageDialog(null, "El código de Tipo de Vehículo ya existe. No se puede guardar.");
                limpiar();
                return; // Sale del método actionPerformed sin continuar
            }
            */    

            //Obtiene datos de la vista y los asigna al modelo
            modelo.setDescripcion(vista.txtDescripcion.getText());
            modelo.setEstado((String) vista.cmbEstado.getSelectedItem());
            
            // Graba y muestra un mensaje de éxito o error
            if (consultas.registrar(modelo)) {
                JOptionPane.showMessageDialog(null, "Tipo de Vehículo se guardó correctamente.");
                limpiar();
                // ACTUALIZA TABLA DEL FORMULARIO
                try {
                    consultas.MostrarTiposVehiculos(vista.tbTiposVehiculos); // vista.tbUsuarios es la tabla donde se mostrarán los usuarios
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al mostrar usuarios: " + ex.getMessage());
                }                         
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar el Tipo de Vehículo.");
                limpiar();
            }
        }    
        
        //Botón: MODIFICAR
        if (e.getSource() == vista.btnModificar) {
            // Valida que campo Id no se encuentre vacía
            if (!vista.txtId_TipoVehiculo.getText().isEmpty()) {            
                // Obtiene datos de la vista y los asigna al modelo
                modelo.setId_TipoVehiculo(Integer.parseInt(vista.txtId_TipoVehiculo.getText()));
                modelo.setDescripcion(vista.txtDescripcion.getText());
                modelo.setEstado((String) vista.cmbEstado.getSelectedItem());
            
                // Modifica registro y muestra un mensaje de éxito o error
                if (consultas.modificar(modelo)) {
                    JOptionPane.showMessageDialog(null, "Tipo de Vehículo se actualizó correctamente.");
                    limpiar();
                    // ACTUALIZA TABLA DEL FORMULARIO
                    try {
                        consultas.MostrarTiposVehiculos(vista.tbTiposVehiculos); // vista.tbUsuarios es la tabla donde se mostrarán los usuarios
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al mostrar usuarios: " + ex.getMessage());
                    }                         
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el Tipo de Vehículo.");
                    limpiar();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un Usuario de la lista.");
                vista.txtId_TipoVehiculo.requestFocus(); // Retorna el foco al campo Id
            }                     
        }
        
        //Botón: ELIMINAR
        if (e.getSource() == vista.btnEliminar) {
            // Valida que campo Id no se encuentre vacía
            if (!vista.txtId_TipoVehiculo.getText().isEmpty()) {                   
                // Obtiene el ID de la vista y lo asigna al modelo
                modelo.setId_TipoVehiculo(Integer.parseInt(vista.txtId_TipoVehiculo.getText()));
            
                // Elimina registro y muestra un mensaje de éxito o error
                if (consultas.eliminar(modelo)) {
                    JOptionPane.showMessageDialog(null, "Tipo de Vehículo se eliminó correctamente.");
                    limpiar();
                    // ACTUALIZA TABLA DEL FORMULARIO
                    try {
                        consultas.MostrarTiposVehiculos(vista.tbTiposVehiculos); // vista.tbUsuarios es la tabla donde se mostrarán los usuarios
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al mostrar usuarios: " + ex.getMessage());
                    }                         
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el Tipo de Vehículo.");
                    limpiar();
                }
             } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un Usuario de la lista.");
                vista.txtId_TipoVehiculo.requestFocus(); // Retorna el foco al campo Id
            }    
        }        
        
        /*
        //Botón: BUSCAR
        if (e.getSource() == vista.btnBuscar) {
            // Obtiene el código de la vista y lo asigna al modelo
            //modelo.setCodigo(vista.txtCodigo.getText());
            
            // Busca el Tipo Vehículo y muestra sus datos o un mensaje de Tipo Vehículo no encontrado
            if (consultas.buscar(modelo)) {
                vista.txtId_TipoVehiculo.setText(String.valueOf(modelo.getId_TipoVehiculo()));
                vista.txtDescripcion.setText(modelo.getDescripcion());                
                vista.cmbEstado.setSelectedItem(modelo.getEstado());                           
            } else {
                JOptionPane.showMessageDialog(null, "Tipo Vehículo no encontrado.");
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
        vista.txtId_TipoVehiculo.setText(null);
        vista.txtDescripcion.setText(null);
        vista.cmbEstado.setSelectedIndex(0); // Indice comienza desde 0 (0=ACTIVO, 1=INACTIVO) 
    }
}    
