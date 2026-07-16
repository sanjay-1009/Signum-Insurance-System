package model;

public class ClaimDocument {

    private int documentId;
    private int claimId;
    private String documentName;
    private String documentUrl;

    public ClaimDocument() {
    }

    public ClaimDocument(
            int claimId,
            String documentName,
            String documentUrl) {

        this.claimId = claimId;
        this.documentName = documentName;
        this.documentUrl = documentUrl;

    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public int getClaimId() {
        return claimId;
    }

    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

}