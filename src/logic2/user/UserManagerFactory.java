/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic2.user;

import logic2.user.UserManager;

/**
 *
 * @author slvai_000
 */
//not really nessecarry, only needed for completing assignment
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