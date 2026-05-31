package servlet;

import controller.DashboardController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    DashboardController controller =
            new DashboardController();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException,
            IOException {

        HashMap<String,Integer> stats =
                controller.getDashboardData();

        response.setContentType("text/html");

        PrintWriter out =
                response.getWriter();

        out.println("<h1>Insurance Dashboard</h1>");
        out.println("<h3>Total Policies : "
                + stats.get("totalPolicies")
                + "</h3>");
        out.println("<h3>Total Claims : "
                + stats.get("totalClaims")
                + "</h3>");
        out.println("<h3>Approved Claims : "
                + stats.get("approvedClaims")
                + "</h3>");
        out.println("<h3>Rejected Claims : "
                + stats.get("rejectedClaims")
                + "</h3>");
        out.println("<h3>Pending Claims : "
                + stats.get("pendingClaims")
                + "</h3>");
    }
}