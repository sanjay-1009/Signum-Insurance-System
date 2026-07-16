package controller;

import dao.AdvisorPolicyDAO;

import java.sql.ResultSet;

public class AdvisorRecommendController {

    AdvisorPolicyDAO dao =
            new AdvisorPolicyDAO();

    public ResultSet getPolicies(
            String insuranceType) {

        return dao.getPolicies(
                insuranceType);

    }

}