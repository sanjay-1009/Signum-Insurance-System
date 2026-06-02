package servlet;

import controller.ClaimDetailsController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

@WebServlet("/getClaim")
public class GetClaimServlet
extends HttpServlet {


private static final long serialVersionUID = 1L;

ClaimDetailsController controller =
        new ClaimDetailsController();

@Override
protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException,
        IOException {

    response.setHeader(
            "Access-Control-Allow-Origin",
            "http://localhost:3000");

    int claimId =
            Integer.parseInt(
                    request.getParameter(
                            "claimId"));

    try {

        ResultSet rs =
                controller.getClaimById(
                        claimId);

        response.setContentType(
                "application/json");

        PrintWriter out =
                response.getWriter();

        if(rs.next()) {

            String json =
                    "{"
                    + "\"claimId\":"
                    + rs.getInt("claim_id")
                    + ","

                    + "\"policyId\":"
                    + rs.getInt("policy_id")
                    + ","

                    + "\"claimantName\":\""
                    + rs.getString(
                            "claimant_name")
                    + "\","

                    + "\"claimAmount\":"
                    + rs.getDouble(
                            "claim_amount")
                    + ","

                    + "\"incidentDate\":\""
                    + rs.getDate(
                            "incident_date")
                    + "\","

                    + "\"description\":\""
                    + rs.getString(
                            "description")
                    + "\","

                    + "\"status\":\""
                    + rs.getString(
                            "status")
                    + "\""

                    + "}";

            out.print(json);

        } else {

            out.print(
                    "{\"message\":\"Claim Not Found\"}"
            );
        }

    } catch(Exception e) {

        e.printStackTrace();
    }
}

@Override
protected void doOptions(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException,
        IOException {

    response.setHeader(
            "Access-Control-Allow-Origin",
            "http://localhost:3000");

    response.setHeader(
            "Access-Control-Allow-Methods",
            "GET,POST,OPTIONS");

    response.setHeader(
            "Access-Control-Allow-Headers",
            "Content-Type");

    response.setStatus(
            HttpServletResponse.SC_OK);
}


}
