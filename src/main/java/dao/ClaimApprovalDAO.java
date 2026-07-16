package dao;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ClaimApprovalDAO {

	public boolean updateStatus(
	        int claimId,
	        String status,
	        String adminRemark) {

	    boolean result = false;

	    try {

	        Connection con =
	                DBConnection.getConnection();

	        String query =
	                "UPDATE claims SET status=?, admin_remark=? WHERE claim_id=?";

	        PreparedStatement ps =
	                con.prepareStatement(query);

	        ps.setString(1, status);
	        ps.setString(2, adminRemark);
	        ps.setInt(3, claimId);

	        int rows =
	                ps.executeUpdate();

	        if(rows > 0) {

	            result = true;

	        }

	    }

	    catch(Exception e) {

	        e.printStackTrace();

	    }

	    return result;

	}
}