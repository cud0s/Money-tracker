/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.user;

/**
 * not really necessary, only needed for completing an assignment
 * @author slvai_000
 */
public final class UserManagerFactory {

    public static UserManager getUserManager() {
        /**
         * @return UserManager
         */
        return new UserManager();
    }

    private UserManagerFactory() {
    }
}
