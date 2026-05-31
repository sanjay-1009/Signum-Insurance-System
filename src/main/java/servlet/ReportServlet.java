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
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out =
                response.getWriter();

        try {

            ResultSet rs =
                    controller.getClaimsReport();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Claim Reports</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h2>Insurance Claim Report</h2>");

            out.println("<table border='1' cellpadding='10'>");

            out.println(
                    "<tr>"
                    + "<th>Claim ID</th>"
                    + "<th>Policy ID</th>"
                    + "<th>Claimant</th>"
                    + "<th>Amount</th>"
                    + "<th>Incident Date</th>"
                    + "<th>Description</th>"
                    + "<th>Status</th>"
                    + "</tr>");

            while(rs.next()) {

                out.println("<tr>");

                out.println("<td>"
                        + rs.getInt("claim_id")
                        + "</td>");

                out.println("<td>"
                        + rs.getInt("policy_id")
                        + "</td>");

                out.println("<td>"
                        + rs.getString("claimant_name")
                        + "</td>");

                out.println("<td>"
                        + rs.getDouble("claim_amount")
                        + "</td>");

                out.println("<td>"
                        + rs.getDate("incident_date")
                        + "</td>");

                out.println("<td>"
                        + rs.getString("description")
                        + "</td>");

                out.println("<td>"
                        + rs.getString("status")
                        + "</td>");

                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}