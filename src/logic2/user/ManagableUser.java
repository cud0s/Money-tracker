/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic2.user;

import java.io.Serializable;

/**
 *
 * @author slvai_000
 * if class implements ManagableUser, it can be used by UserManager class
 */
public interface ManagableUser extends Serializable{

    /**
     * 
     * @return username 
     */
    String getUsername();

    /**
     * Compares user password to inputed passwor 
     * @param inputPsw inputed 
     * @return true if password matches, false if no
     */
    boolean compareToPsw(char[] inputPsw);
}
