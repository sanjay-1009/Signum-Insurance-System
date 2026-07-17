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

        response.setHeader(
                "Access-Control-Allow-Origin",
                "https://insurance-frontend-woad.vercel.app");

        HashMap<String,Integer> stats =
                controller.getDashboardData();

        response.setContentType(
                "application/json");

        PrintWriter out =
                response.getWriter();

        out.print("{");

        out.print("\"totalPolicies\":"
                + stats.get("totalPolicies")
                + ",");

        out.print("\"totalClaims\":"
                + stats.get("totalClaims")
                + ",");

        out.print("\"approvedClaims\":"
                + stats.get("approvedClaims")
                + ",");

        out.print("\"rejectedClaims\":"
                + stats.get("rejectedClaims")
                + ",");

        out.print("\"pendingClaims\":"
                + stats.get("pendingClaims"));

        out.print("}");
    }
    @Override
    protected void doOptions(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException,
            IOException {

        response.setHeader(
                "Access-Control-Allow-Origin",
                "https://insurance-frontend-woad.vercel.app");

        response.setHeader(
                "Access-Control-Allow-Methods",
                "GET, POST, OPTIONS");

        response.setHeader(
                "Access-Control-Allow-Headers",
                "*");

        response.setStatus(
                HttpServletResponse.SC_OK);
    }
}