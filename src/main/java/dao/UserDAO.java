package dao;

import database.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public String validateUser(User user) {

        String role = null;

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "SELECT role FROM users "
                    + "WHERE username=? "
                    + "AND password=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(
                    1,
                    user.getUsername());

            ps.setString(
                    2,
                    user.getPassword());

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                role =
                        rs.getString(
                                "role");
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return role;
    }
    public int getUserId(
            String username) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "SELECT id FROM users WHERE username=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(
                    1,
                    username);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                return rs.getInt("id");
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return 0;
    }
}