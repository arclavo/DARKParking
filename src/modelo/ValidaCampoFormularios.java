
package modelo;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * @author DChangC
 */
public class ValidaCampoFormularios {
    
    // Método para validar Correo Electrónico (minúsculas y caracteres permitidos)
    public static boolean validarCorreoElectronico(String texto) {
        for (char c : texto.toCharArray()) {
            if ((c < 'a' || c > 'z') && c != '@' && c != '.' && c != '-' && c != '_') {
                return false;
            }
        }
        return true;
    }        

    // Método para validar Identificaciones de Clientes (cédula, ruc, etc.)
    public static boolean validarIdentificacion(String texto) {
        // Verifica que el texto no esté vacío
        if (texto.isEmpty()) {
            return false;
        }
        // Verifica que el texto tenga 10 o 13 caracteres y que sean todos numéricos
        if (texto.length() != 10 && texto.length() != 13) {
            return false;
        }
        for (char c : texto.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    // Método para validar campos solo números
    public static boolean validarCampoNumerico(String texto) {
        // Verifica que el texto no esté vacío
        if (texto.isEmpty()) {
            return false;
        }
        // Verifica que todos los caracteres sean numéricos
        for (char c : texto.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    // Método para validar campos solo números
    public static boolean validarTelefono(String texto) {
        // Verifica que el texto no esté vacío
        if (texto.isEmpty()) {
            return false;
        }
        // Verifica que la longitud del texto sea de 9 o 10 dígitos
        int longitud = texto.length();
        if (longitud < 9 || longitud > 10) {
            return false;
        }
        // Verifica que todos los caracteres sean numéricos
        for (char c : texto.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    // Método para validar campos vacíos
    public static boolean validarCampoVacio(String texto) {
        if (texto.isEmpty()) {
            return false;
        }
    return true;
    }
    
    // Método para validar campos solo números enteros
    public static boolean validarCampoEntero(String texto) {
        // Verifica que el texto no esté vacío
        if (texto.isEmpty()) {
            return false;
        }

        // Verifica que todos los caracteres sean numéricos
        for (char c : texto.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }
    return true;
    }

    
    // Método para obtener la fecha y hora actual formateada
    public static String obtenerFechaHoraActualFormateada() {
        // Crear un objeto SimpleDateFormat para formatear la fecha y hora
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        // Obtener la fecha y hora actual
        Date fechaHoraActual = new Date();

        // Formatear la fecha y hora actual como una cadena de texto
        String fechaHoraFormateada = sdf.format(fechaHoraActual);

        // Retornar la fecha y hora formateada
        return fechaHoraFormateada;
    }    
    
    
    /*
    *******************************************************************************************************************************************
    *
    *******************************************************************************************************************************************
    
    public static final int VALIDACION_EXITOSA = 0;
    public static final int IDENTIFICACION_VACIA = 1;
    public static final int LONGITUD_INCORRECTA = 2;
    public static final int FORMATO_INCORRECTO = 3;

    public static int validarIdentificacion1(String texto) {
        // Verifica que el texto no esté vacío
        if (texto.isEmpty()) {
            return IDENTIFICACION_VACIA;
        }
        // Verifica que el texto tenga 10 o 13 caracteres y que sean todos numéricos
        if (texto.length() != 10 && texto.length() != 13) {
            return LONGITUD_INCORRECTA;
        }
        for (char c : texto.toCharArray()) {
            if (!Character.isDigit(c)) {
                return FORMATO_INCORRECTO;
            }
        }
        return VALIDACION_EXITOSA;
    }   
    
    *******************************************************************************************************************************************
    * Colocar este código en el evento del formulario:
    *******************************************************************************************************************************************
    
    private void txtCi_RucFocusLost(java.awt.event.FocusEvent evt) {                                    
        String texto = txtCi_Ruc.getText();
        int resultadoValidacion = ValidaCampoFormularios.validarIdentificacion(texto);
        switch (resultadoValidacion) {
            case ValidaCampoFormularios.VALIDACION_EXITOSA:
                // No hacer nada si la validación es exitosa
                break;
            case ValidaCampoFormularios.IDENTIFICACION_VACIA:
                JOptionPane.showMessageDialog(this, "Identificación no puede estar vacía", "Error", JOptionPane.ERROR_MESSAGE);
                txtCi_Ruc.requestFocusInWindow();
                break;
            case ValidaCampoFormularios.LONGITUD_INCORRECTA:
                JOptionPane.showMessageDialog(this, "Identificación debe tener 10 o 13 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
                txtCi_Ruc.requestFocusInWindow();
                break;
            case ValidaCampoFormularios.FORMATO_INCORRECTO:
                JOptionPane.showMessageDialog(this, "Identificación debe contener solo números", "Error", JOptionPane.ERROR_MESSAGE);
                txtCi_Ruc.requestFocusInWindow();
                break;
            default:
                // Manejar cualquier otro caso si es necesario
                break;
        }
    }
    *******************************************************************************************************************************************   
    */
    
    
    public static boolean validarEdad(int edad) {
        return edad >= 0 && edad <= 150; // Suponiendo que la edad debe estar entre 0 y 150
    }    
    
    
    
    
    
}
