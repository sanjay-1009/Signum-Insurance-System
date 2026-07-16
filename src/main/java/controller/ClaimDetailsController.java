package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.DBConnection;

public class ClaimDetailsController {


public ResultSet getClaimById(
        int claimId) {

    try {

        Connection con =
                DBConnection.getConnection();

        String query =
                "SELECT * FROM claims WHERE claim_id=?";

        PreparedStatement ps =
                con.prepareStatement(
                        query);

        ps.setInt(1, claimId);

        return ps.executeQuery();

    } catch(Exception e) {

        e.printStackTrace();
    }

    return null;
}

public ResultSet getClaimDocuments(int claimId) {

    try {

        Connection con =
                DBConnection.getConnection();

        String query =
                "SELECT * FROM claim_documents WHERE claim_id=?";

        PreparedStatement ps =
                con.prepareStatement(query);

        ps.setInt(1, claimId);

        return ps.executeQuery();

    }

    catch(Exception e) {

        e.printStackTrace();

    }

    return null;

}


}
