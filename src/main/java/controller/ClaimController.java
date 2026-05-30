package controller;

import dao.ClaimDAO;
import model.Claim;

public class ClaimController {

    ClaimDAO dao = new ClaimDAO();

    public boolean submitClaim(
            int policyId,
            String claimantName,
            double claimAmount,
            String incidentDate,
            String description) {

        Claim claim =
                new Claim(
                        policyId,
                        claimantName,
                        claimAmount,
                        incidentDate,
                        description
                );

        return dao.addClaim(claim);
    }
}