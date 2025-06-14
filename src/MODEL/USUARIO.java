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

    public USUARIO(String username, String pasword) {
        this.username = username;
        this.password = pasword;
    }

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
    public boolean validar(String user, String contra){
        return this.username.equals(user)&& this.password.equals(contra);
    }
}
