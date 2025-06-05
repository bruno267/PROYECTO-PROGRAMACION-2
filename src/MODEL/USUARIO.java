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
    private String pasword;

    public USUARIO(String username, String pasword) {
        this.username = username;
        this.pasword = pasword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }
    public boolean validar(String user, String contra){
        return this.username.equals(user)&& this.pasword.equals(contra);
    }
}
