package dao;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdvisorQuestionDAO {

    public ResultSet getQuestions(String insuranceType) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM advisor_questions " +
                    "WHERE insurance_type=? " +
                    "ORDER BY question_order";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, insuranceType);

            return ps.executeQuery();

        }

        catch(Exception e) {

            e.printStackTrace();

        }

        return null;

    }

}