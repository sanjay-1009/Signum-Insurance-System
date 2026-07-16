package dao;

import database.DBConnection;
import model.Policy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PolicyDAO {

    // ===================================================
    // EXISTING METHOD (UNCHANGED)
    // ===================================================

	public int addPolicy(Policy policy) {

	    try {

	        Connection con = DBConnection.getConnection();

	        String query =
	                "INSERT INTO policies(policy_name, policy_type, coverage_amount, premium_amount) VALUES(?,?,?,?)";

	        PreparedStatement ps =
	                con.prepareStatement(
	                        query,
	                        PreparedStatement.RETURN_GENERATED_KEYS);

	        ps.setString(1, policy.getPolicyName());
	        ps.setString(2, policy.getPolicyType());
	        ps.setDouble(3, policy.getCoverageAmount());
	        ps.setDouble(4, policy.getPremiumAmount());

	        int rows = ps.executeUpdate();

	        if (rows > 0) {

	            ResultSet rs = ps.getGeneratedKeys();

	            if (rs.next()) {

	                return rs.getInt(1);

	            }
	        }

	    } catch (Exception e) {

	        e.printStackTrace();

	    }

	    return -1;

	}

    // ===================================================
    // NEW METHOD
    // Save AI Extracted Details
    // ===================================================

    public boolean updateAIDetails(int policyId, Policy policy) {

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "UPDATE policies SET " +
                    "eligibility=?," +
                    "benefits=?," +
                    "exclusions=?," +
                    "covered_diseases=?," +
                    "waiting_period=?," +
                    "required_documents=?," +
                    "claim_process=?," +
                    "suitable_for=?," +
                    "terms_pdf=?," +
                    "ai_summary=? " +
                    "WHERE policy_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, policy.getEligibility());
            ps.setString(2, policy.getBenefits());
            ps.setString(3, policy.getExclusions());
            ps.setString(4, policy.getCoveredDiseases());
            ps.setString(5, policy.getWaitingPeriod());
            ps.setString(6, policy.getRequiredDocuments());
            ps.setString(7, policy.getClaimProcess());
            ps.setString(8, policy.getSuitableFor());
            ps.setString(9, policy.getTermsPdf());
            ps.setString(10, policy.getAiSummary());

            ps.setInt(11, policyId);

            return ps.executeUpdate() > 0;

        } catch(Exception e) {

            e.printStackTrace();

        }

        return false;
    }

    // ===================================================
    // EXISTING METHOD (UNCHANGED)
    // ===================================================

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

    // ===================================================
    // NEW METHOD
    // ===================================================

    public ResultSet getPolicyById(int policyId) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM policies WHERE policy_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, policyId);

            return ps.executeQuery();

        }

        catch(Exception e) {

            e.printStackTrace();

        }

        return null;
    }

    // ===================================================
    // EXISTING METHOD (UNCHANGED)
    // ===================================================

    public ResultSet getPoliciesByUser(int userId) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "SELECT p.* " +
                    "FROM user_policies up " +
                    "JOIN policies p " +
                    "ON up.policy_id = p.policy_id " +
                    "WHERE up.user_id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, userId);

            return ps.executeQuery();

        }

        catch(Exception e) {

            e.printStackTrace();

        }

        return null;
    }

}