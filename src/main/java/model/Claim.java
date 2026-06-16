package model;

public class Claim {

    private int claimId;
    private int policyId;
    private int userId;
    private String claimantName;
    private double claimAmount;
    private String incidentDate;
    private String description;
    private String status;

    public Claim() {
    }

    public Claim(int policyId,
                 String claimantName,
                 double claimAmount,
                 String incidentDate,
                 String description) {

        this.policyId = policyId;
        this.claimantName = claimantName;
        this.claimAmount = claimAmount;
        this.incidentDate = incidentDate;
        this.description = description;
    }

    public int getClaimId() {
        return claimId;
    }

    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    public int getPolicyId() {
        return policyId;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getClaimantName() {
        return claimantName;
    }

    public void setClaimantName(String claimantName) {
        this.claimantName = claimantName;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(String incidentDate) {
        this.incidentDate = incidentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}