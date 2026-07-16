package model;

import java.util.List;

public class AdvisorRequest {

    private String insuranceType;
    private int userId;
    private List<AdvisorAnswer> answers;

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<AdvisorAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AdvisorAnswer> answers) {
        this.answers = answers;
    }
}