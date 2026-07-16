package dao;

import database.DBConnection;
import model.Claim;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClaimDAO {

	public int addClaim(Claim claim) {

	    try {

	        Connection con = DBConnection.getConnection();

	        String query =
	                "INSERT INTO claims(policy_id, user_id, claimant_name, claim_amount, incident_date, description) VALUES(?,?,?,?,?,?)";

	        PreparedStatement ps =
	                con.prepareStatement(
	                        query,
	                        PreparedStatement.RETURN_GENERATED_KEYS
	                );

	        ps.setInt(1, claim.getPolicyId());
	        ps.setInt(2, claim.getUserId());
	        ps.setString(3, claim.getClaimantName());
	        ps.setDouble(4, claim.getClaimAmount());
	        ps.setString(5, claim.getIncidentDate());
	        ps.setString(6, claim.getDescription());

	        ps.executeUpdate();

	        java.sql.ResultSet rs = ps.getGeneratedKeys();

	        if (rs.next()) {

	            return rs.getInt(1);

	        }

	    }

	    catch(Exception e){

	        e.printStackTrace();

	    }

	    return -1;

	}
	// ===================================================
	// NEW METHOD
	// Get All Claims
	// ===================================================

	public ResultSet getAllClaims() {

	    try {

	        Connection con =
	                DBConnection.getConnection();

	        String sql =
	                "SELECT * FROM claims ORDER BY claim_id DESC";

	        PreparedStatement ps =
	                con.prepareStatement(sql);

	        return ps.executeQuery();

	    }

	    catch(Exception e){

	        e.printStackTrace();

	    }

	    return null;

	}
	// ===================================================
	// NEW METHOD
	// Update Claim Status
	// ===================================================

	public boolean updateClaimStatus(

	        int claimId,

	        String status,

	        String adminRemark

	) {

	    try {

	        Connection con =
	                DBConnection.getConnection();

	        String sql =
	                "UPDATE claims SET status=?, admin_remark=?, reviewed_at=NOW() WHERE claim_id=?";

	        PreparedStatement ps =
	                con.prepareStatement(sql);

	        ps.setString(1, status);

	        ps.setString(2, adminRemark);

	        ps.setInt(3, claimId);

	        return ps.executeUpdate() > 0;

	    }

	    catch(Exception e){

	        e.printStackTrace();

	    }

	    return false;

	}
	// ===================================================
	// NEW METHOD
	// Get Claim By Id
	// ===================================================

	public ResultSet getClaimById(int claimId) {

	    try {

	        Connection con =
	                DBConnection.getConnection();

	        String sql =
	                "SELECT * FROM claims WHERE claim_id=?";

	        PreparedStatement ps =
	                con.prepareStatement(sql);

	        ps.setInt(1, claimId);

	        return ps.executeQuery();

	    }

	    catch(Exception e){

	        e.printStackTrace();

	    }

	    return null;

	}
	
}