package dao;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class BuyPolicyDAO {

    public boolean buyPolicy(
            int userId,
            int policyId,
            double premium,
            double maturityAmount) {

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "INSERT INTO user_policies(user_id,policy_id,start_date,end_date,premium_paid,maturity_amount) VALUES(?,?,?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            LocalDate start = LocalDate.now();

            LocalDate end = start.plusYears(1);

            ps.setInt(1, userId);

            ps.setInt(2, policyId);

            ps.setDate(3,
                    java.sql.Date.valueOf(start));

            ps.setDate(4,
                    java.sql.Date.valueOf(end));

            ps.setDouble(5, premium);

            ps.setDouble(6, maturityAmount);

            return ps.executeUpdate() > 0;

        }

        catch(Exception e) {

            e.printStackTrace();

        }

        return false;

    }

}