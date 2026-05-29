package dao;

import database.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public boolean validateUser(User user) {

        boolean status = false;

        try {

            Connection con = DBConnection.getConnection();

            String query =
                    "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}