package controller;

import dao.AdvisorNextQuestionDAO;

import java.sql.ResultSet;

public class AdvisorNextQuestionController {

    AdvisorNextQuestionDAO dao =
            new AdvisorNextQuestionDAO();

    public ResultSet getNextQuestion(

            String insuranceType,

            int questionOrder

    ) {

        return dao.getNextQuestion(

                insuranceType,

                questionOrder

        );

    }

}