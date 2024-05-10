package modelo;

/**
 * @author DChangC
 */
public class Usuarios {
    
    //Atributos (se encapsulan con "private")
    private int id_usuario;             // Identificador único
 // private String codigo;              // Código
    private String apellidos;           // Apellidos
    private String nombres;             // Nombres
    private String telefono;            // Teléfono
    private String email;               // Email
    private String password;            // Password
    private String rol;                 // Rol (Cajero, Administrador, etc)
    private String estado;              // Estado (Activo,Inactivo)
    
    //Métodos Getters and Setters (acceder y modificar valores de los campos)
    public int getId_Usuario() {
        return id_usuario;                      
    }

    public void setId_Usuario(int id_usuario) {         
        this.id_usuario = id_usuario;
    }

    /*
    public String getCodigo() {         
        return codigo;                  
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;           
    }
    */
        
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;                  
    }

    public void setPassword(String password) {
        this.password = password;           
    }
    public String getRol() {
        return rol;                  
    }

    public void setRol(String rol) {
        this.rol = rol;           
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
