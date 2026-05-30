package controller;

import dao.ClaimApprovalDAO;

public class ClaimApprovalController {

    ClaimApprovalDAO dao =
            new ClaimApprovalDAO();

    public boolean updateClaimStatus(
            int claimId,
            String status) {

        return dao.updateStatus(
                claimId,
                status
        );
    }
}