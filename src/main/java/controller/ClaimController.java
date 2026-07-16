package controller;

import dao.ClaimDAO;
import model.Claim;
import java.sql.ResultSet;

public class ClaimController {

    ClaimDAO dao = new ClaimDAO();

    public int submitClaim(
            int policyId,
            int userId,
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
        
        claim.setUserId(userId);

        return dao.addClaim(claim);
    }
 // ===================================================
 // NEW METHOD
 // Get All Claims
 // ===================================================

 public ResultSet getAllClaims() {

     return dao.getAllClaims();

 }
//===================================================
//NEW METHOD
//Get Claim By Id
//===================================================

public ResultSet getClaimById(int claimId) {

  return dao.getClaimById(claimId);

}
//===================================================
//NEW METHOD
//Approve / Reject Claim
//===================================================

public boolean updateClaimStatus(

     int claimId,

     String status,

     String adminRemark

) {

 return dao.updateClaimStatus(

         claimId,

         status,

         adminRemark

 );

}
}