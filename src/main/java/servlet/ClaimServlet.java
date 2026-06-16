package servlet;

import controller.ClaimController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/submitClaim")
public class ClaimServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    ClaimController controller =
            new ClaimController();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    	
    	response.setHeader(
    		    "Access-Control-Allow-Origin",
    		    "http://localhost:3000"
    		);

    		response.setHeader(
    		    "Access-Control-Allow-Methods",
    		    "POST, GET, OPTIONS"
    		);

    		response.setHeader(
    		    "Access-Control-Allow-Headers",
    		    "*"
    		);

        int policyId =
                Integer.parseInt(
                        request.getParameter("policyId"));
        
        int userId =
                Integer.parseInt(
                        request.getParameter("userId"));

        String claimantName =
                request.getParameter("claimantName");

        double claimAmount =
                Double.parseDouble(
                        request.getParameter("claimAmount"));

        String incidentDate =
                request.getParameter("incidentDate");

        String description =
                request.getParameter("description");

        boolean status =
                controller.submitClaim(
                        policyId,
                        userId,
                        claimantName,
                        claimAmount,
                        incidentDate,
                        description
                );

        response.setContentType("text/plain");

        PrintWriter out =
                response.getWriter();

        if(status) {

            out.print("Claim Submitted Successfully");

        } else {

            out.print("Claim Submission Failed");
        }
    }
    @Override
    protected void doOptions(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader(
            "Access-Control-Allow-Origin",
            "http://localhost:3000"
        );

        response.setHeader(
            "Access-Control-Allow-Methods",
            "POST, GET, OPTIONS"
        );

        response.setHeader(
            "Access-Control-Allow-Headers",
            "*"
        );

        response.setStatus(
            HttpServletResponse.SC_OK
        );
    }
}