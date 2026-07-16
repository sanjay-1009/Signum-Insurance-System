package servlet;

import controller.ClaimApprovalController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/approveClaim")
public class ClaimApprovalServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    ClaimApprovalController controller =
            new ClaimApprovalController();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException,
            IOException {
    	
    	response.setHeader(
    		    "Access-Control-Allow-Origin",
    		    "http://localhost:3000");

    		response.setHeader(
    		    "Access-Control-Allow-Methods",
    		    "POST, GET, OPTIONS");

    		response.setHeader(
    		    "Access-Control-Allow-Headers",
    		    "*");

        int claimId =
                Integer.parseInt(
                        request.getParameter(
                                "claimId"));

        String status =
                request.getParameter(
                        "status");
        
        String adminRemark =
                request.getParameter(
                        "adminRemark");

        boolean result =
                controller.updateClaimStatus(
                        claimId,
                        status,
                        adminRemark
                );

        response.setContentType(
                "text/plain");

        PrintWriter out =
                response.getWriter();

        if(result) {

            out.print(
                    "Claim Status Updated");

        } else {

            out.print(
                    "Update Failed");
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
            "POST, GET, OPTIONS");

        response.setHeader(
            "Access-Control-Allow-Headers",
            "*");

        response.setStatus(
            HttpServletResponse.SC_OK);
    }
}