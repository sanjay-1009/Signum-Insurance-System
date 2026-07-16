package controller;

import dao.AdvisorQuestionDAO;

import java.sql.ResultSet;

public class AdvisorQuestionController {

    AdvisorQuestionDAO dao =
            new AdvisorQuestionDAO();

    public ResultSet getQuestions(
            String insuranceType) {

        return dao.getQuestions(
                insuranceType);

    }

}