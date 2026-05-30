package dao;

import database.DBConnection;
import model.Claim;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ClaimDAO {

    public boolean addClaim(Claim claim) {

        boolean status = false;

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "INSERT INTO claims(policy_id, claimant_name, claim_amount, incident_date, description) VALUES(?,?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, claim.getPolicyId());
            ps.setString(2, claim.getClaimantName());
            ps.setDouble(3, claim.getClaimAmount());
            ps.setString(4, claim.getIncidentDate());
            ps.setString(5, claim.getDescription());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}