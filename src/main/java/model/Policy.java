package model;

public class Policy {

    private int policyId;
    private String policyName;
    private String policyType;
    private double coverageAmount;
    private double premiumAmount;

    public Policy() {
    }

    public Policy(String policyName,
                  String policyType,
                  double coverageAmount,
                  double premiumAmount) {

        this.policyName = policyName;
        this.policyType = policyType;
        this.coverageAmount = coverageAmount;
        this.premiumAmount = premiumAmount;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public double getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }
}