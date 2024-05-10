package modelo;

/**
 * @author DChangC
 */
public class Clientes {
    
    //Atributos (se encapsulan con "private")
    private int id_cliente;             // Identificador único 
    private String codigo;              // Código
    private String apellidos;           // Apellidos
    private String nombres;             // Nombres
    private String direccion;           // Dirección
    private String telefono;            // Telefono (Celular o Convencional)
    private String ci_ruc;              // Identificación (Cédula, Ruc, Pasaporte)
    private String email;               // Correo Electrónico
    private String estado;              // Estado (Activo,Inactivo)
    
    //Métodos Getters and Setters (acceder y modificar valores de los campos)
    public int getId_Cliente() {
        return id_cliente;                      
    }

    public void setId_Cliente(int id_cliente) {         
        this.id_cliente = id_cliente;                   
    }

    public String getCodigo() {         
        return codigo;                  
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;           
    }
    
    public String getApellidos() {
        return apellidos;                  
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;           
    }

    public String getNombres() {        
        return nombres;                 
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;         
    }

    public String getDireccion() {
        return direccion;                  
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;           
    }
    public String getTelefono() {
        return telefono;                  
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;           
    }
    public String getCi_Ruc() {
        return ci_ruc;                  
    }

    public void setCi_Ruc(String ci_ruc) {
        this.ci_ruc = ci_ruc;           
    }
    public String getEmail() {
        return email;                  
    }

    public void setEmail(String email) {
        this.email = email;           
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
