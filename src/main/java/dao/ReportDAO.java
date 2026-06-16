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
            String username) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM claims "
                    + "WHERE LOWER(claimant_name)=LOWER(?)";

            PreparedStatement ps =
                    con.prepareStatement(
                            query);

            ps.setString(
                    1,
                    username);

            return ps.executeQuery();

        } catch(Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}