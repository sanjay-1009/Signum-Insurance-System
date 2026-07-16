package dao;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdvisorPolicyDAO {

    public ResultSet getPolicies(
            String insuranceType) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
            		"SELECT * FROM policies WHERE policy_type LIKE ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    insuranceType + "%");

            return ps.executeQuery();

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

}