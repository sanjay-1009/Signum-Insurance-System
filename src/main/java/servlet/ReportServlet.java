package servlet;

import controller.ReportController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

@WebServlet("/reports")
public class ReportServlet extends HttpServlet {


private static final long serialVersionUID = 1L;

ReportController controller =
        new ReportController();

@Override
protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException,
        IOException {

    response.setHeader(
            "Access-Control-Allow-Origin",
            "http://localhost:3000");

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    PrintWriter out =
            response.getWriter();

    try {

        ResultSet rs =
                controller.getClaimsReport();

        StringBuilder json =
                new StringBuilder();

        json.append("[");

        boolean first = true;

        while(rs.next()) {

            if(!first) {

                json.append(",");
            }

            json.append("{");

            json.append(
                    "\"claimId\":")
                    .append(
                            rs.getInt(
                                    "claim_id"))
                    .append(",");

            json.append(
                    "\"policyId\":")
                    .append(
                            rs.getInt(
                                    "policy_id"))
                    .append(",");

            json.append(
                    "\"claimantName\":\"")
                    .append(
                            rs.getString(
                                    "claimant_name"))
                    .append("\",");

            json.append(
                    "\"claimAmount\":")
                    .append(
                            rs.getDouble(
                                    "claim_amount"))
                    .append(",");

            json.append(
                    "\"incidentDate\":\"")
                    .append(
                            rs.getDate(
                                    "incident_date"))
                    .append("\",");

            String description = rs.getString("description");

            if (description == null) {
                description = "";
            }

            description = description
                    .replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "");

            json.append("\"description\":\"")
                .append(description)
                .append("\",");

            json.append(
                    "\"status\":\"")
                    .append(
                            rs.getString(
                                    "status"))
                    .append("\"");

            json.append("}");

            first = false;
        }

        json.append("]");
        
        out.flush();

        out.print(
                json.toString());

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
