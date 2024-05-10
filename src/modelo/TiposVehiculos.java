
package modelo;

/**
 * @author DChangC
 */
public class TiposVehiculos {
    //Atributos (se encapsulan con "private")
    private int id_tipovehiculo;        // Identificador único
 // private String codigo;              // Codigo
    private String descripcion;         // Descripción
    private String estado;              // Estado (Activo,Inactivo)

    //Métodos Getters and Setters (acceder y modificar valores de los campos)
    public int getId_TipoVehiculo() {
        return id_tipovehiculo;                      
    }

    public void setId_TipoVehiculo(int id_tipovehiculo) {         
        this.id_tipovehiculo = id_tipovehiculo;                   
    }

    /*
    public String getCodigo() {
        return codigo;                      
    }

    public void setCodigo(String codigo) {         
        this.codigo = codigo;                   
    }
    */

    public String getDescripcion() {
        return descripcion;                      
    }

    public void setDescripcion(String descripcion) {         
        this.descripcion = descripcion;                   
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
