package model;

public class Policy {

    private int policyId;
    private String policyName;
    private String policyType;
    private double coverageAmount;
    private double premiumAmount;

    // =========================
    // AI Extracted Fields
    // =========================

    private String eligibility;
    private String benefits;
    private String exclusions;
    private String coveredDiseases;
    private String waitingPeriod;
    private String requiredDocuments;
    private String claimProcess;
    private String suitableFor;
    private String termsPdf;
    private String aiSummary;

    public Policy() {
    }

    // Existing constructor (DO NOT REMOVE)
    public Policy(String policyName,
                  String policyType,
                  double coverageAmount,
                  double premiumAmount) {

        this.policyName = policyName;
        this.policyType = policyType;
        this.coverageAmount = coverageAmount;
        this.premiumAmount = premiumAmount;
    }

    // New constructor for AI fields
    public Policy(String policyName,
                  String policyType,
                  double coverageAmount,
                  double premiumAmount,
                  String eligibility,
                  String benefits,
                  String exclusions,
                  String coveredDiseases,
                  String waitingPeriod,
                  String requiredDocuments,
                  String claimProcess,
                  String suitableFor,
                  String termsPdf,
                  String aiSummary) {

        this.policyName = policyName;
        this.policyType = policyType;
        this.coverageAmount = coverageAmount;
        this.premiumAmount = premiumAmount;
        this.eligibility = eligibility;
        this.benefits = benefits;
        this.exclusions = exclusions;
        this.coveredDiseases = coveredDiseases;
        this.waitingPeriod = waitingPeriod;
        this.requiredDocuments = requiredDocuments;
        this.claimProcess = claimProcess;
        this.suitableFor = suitableFor;
        this.termsPdf = termsPdf;
        this.aiSummary = aiSummary;
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

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getExclusions() {
        return exclusions;
    }

    public void setExclusions(String exclusions) {
        this.exclusions = exclusions;
    }

    public String getCoveredDiseases() {
        return coveredDiseases;
    }

    public void setCoveredDiseases(String coveredDiseases) {
        this.coveredDiseases = coveredDiseases;
    }

    public String getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(String waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public String getRequiredDocuments() {
        return requiredDocuments;
    }

    public void setRequiredDocuments(String requiredDocuments) {
        this.requiredDocuments = requiredDocuments;
    }

    public String getClaimProcess() {
        return claimProcess;
    }

    public void setClaimProcess(String claimProcess) {
        this.claimProcess = claimProcess;
    }

    public String getSuitableFor() {
        return suitableFor;
    }

    public void setSuitableFor(String suitableFor) {
        this.suitableFor = suitableFor;
    }

    public String getTermsPdf() {
        return termsPdf;
    }

    public void setTermsPdf(String termsPdf) {
        this.termsPdf = termsPdf;
    }

    public String getAiSummary() {
        return aiSummary;
    }

    public void setAiSummary(String aiSummary) {
        this.aiSummary = aiSummary;
    }
}