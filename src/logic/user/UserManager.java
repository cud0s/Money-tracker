/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.user;

import programIO.FileIO;
import java.util.HashMap;

/**
 *
 * @author slvai_000
 */
public class UserManager {

    private HashMap users;
    private FileIO fileIO;

    /**
     * Registers user with user manager
     *
     * @param newUsn username
     * @param newPassword password
     * @return new User, which was registered
     * @throws RuntimeException with text "This username is not available" if
     * username and password combination is invalid
     */
    public Object registerUser(String newUsn, char[] newPassword) {
        return addUser(new User(newUsn, newPassword));
    }   // registers User

    /**
     * Registers user with user manager
     *
     * @param newUsn username
     * @param newPassword password
     * @param newAge age
     * @param newBudget budget
     * @return new MtUser, which was registered
     * @throws RuntimeException with text "This username is not available" if
     * username and password combination is invalid
     */
    public Object registerUser(String newUsn, char[] newPassword, int newAge, int newBudget) {
        return addUser(new MTUser(newUsn, newPassword, newAge, newBudget));
        //creation of admin should go here
    }  //registers MTUser

    /**
     * Tries to log in with user manager
     *
     * @param inputUsn username
     * @param inputPsw password
     * @return user if login was successful
     * @throws RuntimeException with text "Login unsuccessful, try again" if
     * login failed
     */
    public Object performLogin(String inputUsn, char[] inputPsw) {
        ManagableUser newUser = (ManagableUser) users.get(inputUsn);
        if (newUser != null) {
            if (newUser.compareToPsw(inputPsw)) {
                return newUser;
            }
        }
        throw new RuntimeException("Login unsuccessful, try again");
    }
    
    public void writeDataToDisk(){
        fileIO.updateFile(users);
    }

    private Object addUser(ManagableUser newUser) {
        if (users.put(newUser.getUsername(), newUser) == null) {
            fileIO.updateFile(users);
            return newUser;
        } else {
            throw new RuntimeException("This username is not available");
        }
    }

    UserManager() {
        fileIO = new FileIO();
        users = (HashMap)fileIO.getObjects();
        if (users == null) {
            users = new HashMap();
        }
        //FileWriter fileWriter = new FileWriter("mt");
    }
}
