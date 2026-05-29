package servlet;

import controller.PolicyController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addPolicy")
public class PolicyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    PolicyController controller =
            new PolicyController();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String policyName =
                request.getParameter("policyName");

        String policyType =
                request.getParameter("policyType");

        double coverageAmount =
                Double.parseDouble(
                        request.getParameter(
                                "coverageAmount"));

        double premiumAmount =
                Double.parseDouble(
                        request.getParameter(
                                "premiumAmount"));

        boolean status =
                controller.addPolicy(
                        policyName,
                        policyType,
                        coverageAmount,
                        premiumAmount
                );

        response.setContentType("text/plain");

        PrintWriter out =
                response.getWriter();

        if(status) {

            out.print("Policy Added Successfully");

        } else {

            out.print("Policy Addition Failed");
        }
    }
}