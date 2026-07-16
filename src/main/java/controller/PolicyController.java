package controller;

import dao.PolicyDAO;
import model.Policy;

import java.sql.ResultSet;

public class PolicyController {

    PolicyDAO dao = new PolicyDAO();

    // ===================================================
    // EXISTING METHOD (UNCHANGED)
    // ===================================================

    public int addPolicy(
            String policyName,
            String policyType,
            double coverageAmount,
            double premiumAmount) {

        Policy policy = new Policy(
                policyName,
                policyType,
                coverageAmount,
                premiumAmount
        );

        return dao.addPolicy(policy);
    }

    // ===================================================
    // NEW METHOD
    // Save AI Extracted Details
    // ===================================================

    public boolean updateAIDetails(
            int policyId,
            Policy policy) {

        return dao.updateAIDetails(
                policyId,
                policy);

    }

    // ===================================================
    // EXISTING METHOD (UNCHANGED)
    // ===================================================

    public ResultSet getAllPolicies() {

        return dao.getAllPolicies();

    }

    // ===================================================
    // NEW METHOD
    // ===================================================

    public ResultSet getPolicyById(int policyId) {

        return dao.getPolicyById(policyId);

    }

    // ===================================================
    // EXISTING METHOD (UNCHANGED)
    // ===================================================

    public ResultSet getPoliciesByUser(int userId) {

        return dao.getPoliciesByUser(userId);

    }

}