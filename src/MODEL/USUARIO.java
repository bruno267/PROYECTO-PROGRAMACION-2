/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import com.sun.glass.events.WindowEvent;

/**
 *
 * @author comas
 */
public class USUARIO {

    private String username;
    private String password;
    private String fechaRegistro; // Nuevo campo

    // Constructor actualizado
    public USUARIO(String username, String password) {
        this.username = username;
        this.password = password;
        this.fechaRegistro = java.time.LocalDate.now().toString(); // Asigna fecha actual por defecto
    }

    // Constructor adicional por si deseas establecer la fecha manualmente
    public USUARIO(String username, String password, String fechaRegistro) {
        this.username = username;
        this.password = password;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean validar(String user, String contra) {
        return this.username.equals(user) && this.password.equals(contra);
    }
}
