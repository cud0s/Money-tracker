/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoneyTracker;

import java.util.Arrays;
import java.util.Objects;

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
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Arrays.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }
    
    public User(String newUsername, char[] newPassword) {
        username = new String();
        username = newUsername;

        //should hash password
        password = newPassword.clone();
        Arrays.fill(newPassword, '\0');
    }
}
