package controller;

import dao.UserDAO;
import model.User;

public class LoginController {

    UserDAO dao = new UserDAO();

    public boolean login(String username, String password) {

        User user = new User(username, password);

        return dao.validateUser(user);
    }
}