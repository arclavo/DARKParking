package darkparking;

import java.sql.SQLException;
import javax.swing.*;
import vista.frmLogin;

/*
 ***********************************************************
 * Tarea Virtual: Proyecto Dark Parking
 * Grupo: 2
 ***********************************************************
 */

public class MainMVC {

 // public static void main(String[] args) {
    public static void main(String[] args) throws SQLException {
/*
        // Mostrar el formulario de login
        SwingUtilities.invokeLater(() -> {
            frmLogin loginForm = new frmLogin();
            loginForm.setVisible(true);
        });

        // Esperar hasta que se complete el proceso de autenticación
        // Luego, mostrar el formulario de menú
        // Supongamos que el formulario de login establece una bandera o evento cuando se completa la autenticación
        // y que hay un método estático en frmLogin que permite verificar si la autenticación ha sido exitosa
        while (!frmLogin.isAuthenticationSuccessful()) {
            try {
                Thread.sleep(1000); // Esperar 1 segundo antes de verificar la autenticación nuevamente
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Si la autenticación fue exitosa, mostrar el formulario de menú
        SwingUtilities.invokeLater(() -> {
            frmMenu menuForm = new frmMenu();
            menuForm.setVisible(true);
        });
*/

        // Crear instancia del formulario frmLogin
        frmLogin loginForm = new frmLogin();
        
        // Hacer visible el formulario
        loginForm.setVisible(true);
    }
}

