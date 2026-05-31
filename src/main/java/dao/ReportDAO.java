package dao;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReportDAO {

    public ResultSet getAllClaims() {

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM claims";

            PreparedStatement ps =
                    con.prepareStatement(query);

            return ps.executeQuery();

        } catch(Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}