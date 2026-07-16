package controller;

import dao.UserPolicyDAO;

import java.sql.ResultSet;

public class UserPolicyController {

    UserPolicyDAO dao =
            new UserPolicyDAO();

    public ResultSet getPolicy(int userId){

        return dao.getUserPolicy(userId);

    }

}