/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackendSystemPackage;

import BackendSystemPackage.Login;

/**
 *
 * @author RH
 */
public class User implements Login {
    protected String username;
    protected String password;
    
    public User (String username, String password){
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean Login(String username, String password) {
        return username.equals(this.username) && password.equals(this.password);
    }
    
    public User(){}
}
