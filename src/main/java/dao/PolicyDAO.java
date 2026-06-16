package dao;

import database.DBConnection;
import model.Policy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PolicyDAO {

    public boolean addPolicy(Policy policy) {

        boolean status = false;

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "INSERT INTO policies(policy_name, policy_type, coverage_amount, premium_amount) VALUES(?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, policy.getPolicyName());
            ps.setString(2, policy.getPolicyType());
            ps.setDouble(3, policy.getCoverageAmount());
            ps.setDouble(4, policy.getPremiumAmount());

            int rows = ps.executeUpdate();

            if(rows > 0) {
                status = true;
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    public ResultSet getAllPolicies() {

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM policies";

            PreparedStatement ps =
                    con.prepareStatement(query);

            return ps.executeQuery();

        } catch(Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}