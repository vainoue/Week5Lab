/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author vitor
 */
public class AccountService {
    
    public User login(String username, String password) {
        User user = new User(username, password);
        
        if ((username.equals("abe") || username.equals("barb")) && password.equals("password")) {
            user.setPassword(null);
            return user;
        }
        
        return null;
    }
    
}
