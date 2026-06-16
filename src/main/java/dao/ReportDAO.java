package dao;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReportDAO {

    public ResultSet getAllClaims() {

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM claims";

            PreparedStatement ps =
                    con.prepareStatement(query);

            return ps.executeQuery();

        } catch(Exception e) {

            e.printStackTrace();
        }

        return null;
    }
    public ResultSet getClaimsByUser(
            int userId) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM claims WHERE user_id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, userId);

            return ps.executeQuery();

        } catch(Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}