/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic2.user;

import logic2.user.ManagableUser;
import java.util.Arrays;

/**
 *
 * @author slvai_000
 */
public class User implements ManagableUser {

    private String username;
    private final char[] password;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean compareToPsw(char[] inputPsw) {
        //hashing and etc.
        return Arrays.equals(password, inputPsw);
    }
    
    /**
     * 
     * @param newUsername username 
     * @param newPassword password
     */
    public User(String newUsername, char[] newPassword) {
        username = new String();
        username = newUsername;

        //should hash password
        password = newPassword.clone();
        Arrays.fill(newPassword, '\0');
    }
}
