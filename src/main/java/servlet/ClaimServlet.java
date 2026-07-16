package servlet;

import controller.ClaimController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import java.io.File;
import java.util.Collection;

import controller.ClaimDocumentController;
import service.FileUploadService;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import java.sql.ResultSet;

@WebServlet("/submitClaim")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,
	    maxFileSize = 10 * 1024 * 1024,
	    maxRequestSize = 50 * 1024 * 1024
	)
public class ClaimServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    ClaimController controller =
            new ClaimController();
    
    ClaimDocumentController documentController =
            new ClaimDocumentController();

    FileUploadService uploadService =
            new FileUploadService();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    	
    	System.out.println("policyId = " + request.getParameter("policyId"));
    	System.out.println("userId = " + request.getParameter("userId"));
    	System.out.println("claimAmount = " + request.getParameter("claimAmount"));
    	
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

        int claimId =
                controller.submitClaim(
                        policyId,
                        userId,
                        claimantName,
                        claimAmount,
                        incidentDate,
                        description
                );

        boolean status = claimId > 0;

        if (status) {

            try {

                Collection<Part> parts = request.getParts();

                for (Part part : parts) {

                    if (!"documents".equals(part.getName()) ||
                            part.getSize() == 0) {

                        continue;

                    }

                    String fileName =
                            part.getSubmittedFileName();

                    File tempFile =
                            File.createTempFile(
                                    "claim_",
                                    "_" + fileName
                            );

                    Files.copy(
                            part.getInputStream(),
                            tempFile.toPath(),
                            StandardCopyOption.REPLACE_EXISTING
                    );

                    String cloudUrl =
                            uploadService.upload(tempFile);

                    documentController.saveDocument(

                            claimId,

                            fileName,

                            cloudUrl

                    );

                    tempFile.delete();

                }

            }

            catch(Exception e){

                e.printStackTrace();

            }

        }

        response.setContentType("text/plain");

        PrintWriter out = response.getWriter();

        if(status){

            out.print("Claim Submitted Successfully");

        }
        else{

            out.print("Claim Submission Failed");

        }
    }
    
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader(
                "Access-Control-Allow-Origin",
                "http://localhost:3000");

        response.setContentType("application/json");

        PrintWriter out =
                response.getWriter();

        try {

            ResultSet rs =
                    controller.getAllClaims();

            StringBuilder json =
                    new StringBuilder();

            json.append("[");

            boolean first = true;

            while(rs.next()){

                if(!first){

                    json.append(",");

                }

                json.append("{");

                json.append("\"claimId\":")
                        .append(rs.getInt("claim_id"))
                        .append(",");

                json.append("\"policyId\":")
                        .append(rs.getInt("policy_id"))
                        .append(",");

                json.append("\"userId\":")
                        .append(rs.getInt("user_id"))
                        .append(",");

                json.append("\"claimantName\":\"")
                        .append(rs.getString("claimant_name"))
                        .append("\",");

                json.append("\"claimAmount\":")
                        .append(rs.getDouble("claim_amount"))
                        .append(",");

                json.append("\"incidentDate\":\"")
                        .append(rs.getDate("incident_date"))
                        .append("\",");

                json.append("\"description\":\"")
                        .append(rs.getString("description"))
                        .append("\",");

                json.append("\"status\":\"")
                        .append(rs.getString("status"))
                        .append("\"");

                json.append("}");

                first = false;

            }

            json.append("]");

            out.print(json);

        }

        catch(Exception e){

            e.printStackTrace();

            out.print("[]");

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