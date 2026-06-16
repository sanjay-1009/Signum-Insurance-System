package controller;

import dao.ReportDAO;

import java.sql.ResultSet;

public class ReportController {

    ReportDAO dao =
            new ReportDAO();

    public ResultSet getClaimsReport() {

        return dao.getAllClaims();
    }
    public ResultSet getClaimsByUser(
            String username) {

        return dao.getClaimsByUser(
                username);
    }
}