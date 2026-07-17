package servlet;

import controller.BuyPolicyController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/buyPolicy")
public class BuyPolicyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    BuyPolicyController controller =
            new BuyPolicyController();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader(
                "Access-Control-Allow-Origin",
                "https://insurance-frontend-woad.vercel.app");

        response.setHeader(
                "Access-Control-Allow-Methods",
                "POST, OPTIONS");

        response.setHeader(
                "Access-Control-Allow-Headers",
                "*");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        try {

            int userId =
                    Integer.parseInt(
                            request.getParameter("userId"));

            int policyId =
                    Integer.parseInt(
                            request.getParameter("policyId"));

            double premium =
                    Double.parseDouble(
                            request.getParameter("premium"));

            double maturityAmount =
                    Double.parseDouble(
                            request.getParameter("maturityAmount"));

            boolean status =
                    controller.buyPolicy(
                            userId,
                            policyId,
                            premium,
                            maturityAmount);

            if(status) {

                out.print(
                        "{\"status\":\"SUCCESS\",\"message\":\"Policy Purchased Successfully\"}");

            } else {

                out.print(
                        "{\"status\":\"FAILED\",\"message\":\"Purchase Failed\"}");

            }

        }

        catch(Exception e) {

            e.printStackTrace();

            out.print(
                    "{\"status\":\"FAILED\",\"message\":\"Server Error\"}");

        }

    }

    @Override
    protected void doOptions(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader(
                "Access-Control-Allow-Origin",
                "https://insurance-frontend-woad.vercel.app");

        response.setHeader(
                "Access-Control-Allow-Methods",
                "POST, OPTIONS");

        response.setHeader(
                "Access-Control-Allow-Headers",
                "*");

        response.setStatus(
                HttpServletResponse.SC_OK);

    }

}