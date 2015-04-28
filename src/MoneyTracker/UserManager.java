/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoneyTracker;

import java.util.HashMap;

/**
 *
 * @author slvai_000
 */
public class UserManager {

    private final HashMap users;

    public Object registerUser(String newUsn, char[] newPassword) {
        return addUser(new User(newUsn, newPassword));
    }   // registers User

    public Object registerUser(String newUsn, char[] newPassword, int newAge, int newBudget) {
        return addUser(new MTUser(newUsn, newPassword, newAge, newBudget));
        //creation of admin should go here
    }  //registers MTUser

    public Object performLogin(String inputUsn, char[] inputPsw) {
        ManagableUser newUser = (ManagableUser) users.get(inputUsn);
        if (newUser != null) {
            if (newUser.compareToPsw(inputPsw)) {
                return newUser;
            }
        }
        throw new RuntimeException("Login unsuccessful, try again");
    }

    private Object addUser(ManagableUser newUser) {
        if (users.put(newUser.getUsername(), newUser) == null) {
            return newUser;
        } else {
            throw new RuntimeException("This username is not available");
        }
    }

    UserManager() {
        users = new HashMap();
    }
}
