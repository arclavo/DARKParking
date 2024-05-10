/*
 *******************************************************************************
 * Clase que representa una conexión a una BD en MySQL                         *
 * Se utiliza JDBC para seguridad y eficiencia.                                *
 *******************************************************************************
*/

package modelo;

import java.sql.Connection;     // Envía consultas SQL y recibe resultados
import java.sql.DriverManager;  // Gestiona controladores JDBC
import java.sql.SQLException;   // Maneja errores al interactuar con la BD

/**
 * @author DChangC
 */
public class Conexion {
    Connection con = null;              // Objeto "Connection" para almacenar la conexión a la BD

    String base = "bdDarkParking";    //Nombre de la base de datos
    String url = "jdbc:mysql://localhost:3306/" + base; //Dirección, puerto y nombre de la BD
    String user = "root";               //Usuario de acceso a MySQL
    String password = "";               //Password del usuario

    // Método que establece y retorna una conexión a la BD.
    public Connection getConexion(){

        try {
            // Se carga la clase del driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Se establece conexión a la BD usando URL, usuario y contraseña
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex){
            // Si ocurre un error al cargar o conectar, muestra el error
            System.err.println(ex);
        }
        // Se retorna la conexión establecida
        return con;
    }
}
