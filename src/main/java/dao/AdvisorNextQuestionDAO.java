package dao;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdvisorNextQuestionDAO {

    public ResultSet getNextQuestion(

            String insuranceType,

            int questionOrder

    ) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM advisor_questions "
                  + "WHERE insurance_type=? "
                  + "AND question_order=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, insuranceType);

            ps.setInt(2, questionOrder);

            return ps.executeQuery();

        }

        catch(Exception e){

            e.printStackTrace();

        }

        return null;

    }

}