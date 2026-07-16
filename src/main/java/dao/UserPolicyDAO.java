package dao;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserPolicyDAO {

    public ResultSet getUserPolicy(int userId) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =

            "SELECT " +
            "p.policy_id, " +          // <-- Added
            "p.policy_name, " +
            "p.policy_type, " +
            "up.premium_paid, " +
            "up.maturity_amount, " +
            "up.start_date, " +
            "up.end_date " +

            "FROM user_policies up " +

            "JOIN policies p " +
            "ON up.policy_id = p.policy_id " +

            "WHERE up.user_id = ?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, userId);

            return ps.executeQuery();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

}