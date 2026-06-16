package controller;

import dao.PolicyDAO;
import model.Policy;

import java.sql.ResultSet;

public class PolicyController {

    PolicyDAO dao = new PolicyDAO();

    public boolean addPolicy(
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

    public ResultSet getAllPolicies() {

        return dao.getAllPolicies();
    }
}