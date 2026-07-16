package controller;

import dao.ClaimDocumentDAO;
import model.ClaimDocument;


public class ClaimDocumentController {

    ClaimDocumentDAO dao =
            new ClaimDocumentDAO();

    public boolean saveDocument(

            int claimId,

            String documentName,

            String documentUrl

    ) {

        ClaimDocument document =
                new ClaimDocument(

                        claimId,

                        documentName,

                        documentUrl

                );

        return dao.saveDocument(document);

    }
    
 // ===================================================
 // NEW METHOD
 // Get Documents by Claim ID
 // ===================================================

 public java.sql.ResultSet getDocumentsByClaimId(int claimId) {

     return dao.getDocumentsByClaimId(claimId);

 }

}