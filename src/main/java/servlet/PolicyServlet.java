package servlet;

import controller.PolicyController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;



@WebServlet("/policy")

public class PolicyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    PolicyController controller = new PolicyController();
    
   

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "*");

        String policyName = request.getParameter("policyName");
        String policyType = request.getParameter("policyType");

        double coverageAmount =
                Double.parseDouble(request.getParameter("coverageAmount"));

        double premiumAmount =
                Double.parseDouble(request.getParameter("premiumAmount"));

        // Step 1: Insert basic policy
        int policyId = controller.addPolicy(
                policyName,
                policyType,
                coverageAmount,
                premiumAmount);

        boolean status = false;

        // Step 2: Save AI details
        if (policyId > 0) {

            model.Policy aiPolicy = new model.Policy();

            aiPolicy.setEligibility(request.getParameter("eligibility"));

            aiPolicy.setBenefits(request.getParameter("benefits"));

            aiPolicy.setExclusions(request.getParameter("exclusions"));

            aiPolicy.setCoveredDiseases(request.getParameter("coveredDiseases"));

            aiPolicy.setWaitingPeriod(request.getParameter("waitingPeriod"));

            aiPolicy.setRequiredDocuments(request.getParameter("requiredDocuments"));

            aiPolicy.setClaimProcess(request.getParameter("claimProcess"));

            aiPolicy.setSuitableFor(request.getParameter("whoShouldBuy"));

            aiPolicy.setAiSummary(request.getParameter("plainEnglishSummary"));

            status = controller.updateAIDetails(policyId, aiPolicy);
        }

        response.setContentType("text/plain");

        if (status) {

            response.getWriter().print("Policy Added Successfully");

        } else {

            response.getWriter().print("Policy Addition Failed");

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

        PrintWriter out = response.getWriter();

        try {

            String id = request.getParameter("id");

            // ===========================
            // Return ONE policy
            // ===========================
            if (id != null && !id.isEmpty()) {

                ResultSet rs =
                        controller.getPolicyById(
                                Integer.parseInt(id));

                if (rs.next()) {

                    StringBuilder json = new StringBuilder();

                    json.append("{");

                    json.append("\"policyId\":")
                            .append(rs.getInt("policy_id"))
                            .append(",");

                    json.append("\"policyName\":\"")
                            .append(rs.getString("policy_name"))
                            .append("\",");

                    json.append("\"policyType\":\"")
                            .append(rs.getString("policy_type"))
                            .append("\",");

                    json.append("\"coverageAmount\":")
                            .append(rs.getDouble("coverage_amount"))
                            .append(",");

                    json.append("\"premiumAmount\":")
                            .append(rs.getDouble("premium_amount"))
                            .append(",");

                    json.append("\"eligibility\":\"")
                            .append(rs.getString("eligibility"))
                            .append("\",");

                    json.append("\"benefits\":\"")
                            .append(rs.getString("benefits"))
                            .append("\",");

                    json.append("\"exclusions\":\"")
                            .append(rs.getString("exclusions"))
                            .append("\",");

                    json.append("\"coveredDiseases\":\"")
                            .append(rs.getString("covered_diseases"))
                            .append("\",");

                    json.append("\"waitingPeriod\":\"")
                            .append(rs.getString("waiting_period"))
                            .append("\",");

                    json.append("\"requiredDocuments\":\"")
                            .append(rs.getString("required_documents"))
                            .append("\",");

                    json.append("\"claimProcess\":\"")
                            .append(rs.getString("claim_process"))
                            .append("\",");

                    json.append("\"whoShouldBuy\":\"")
                            .append(rs.getString("suitable_for"))
                            .append("\",");

                    json.append("\"plainEnglishSummary\":\"")
                            .append(rs.getString("ai_summary"))
                            .append("\"");

                    json.append("}");

                    out.print(json.toString());

                } else {

                    out.print("{}");

                }

                return;
            }

            // ===========================
            // Return ALL policies
            // ===========================
            ResultSet rs = controller.getAllPolicies();

            StringBuilder json = new StringBuilder();

            json.append("[");

            boolean first = true;

            while (rs.next()) {

                if (!first) {

                    json.append(",");

                }

                json.append("{");

                json.append("\"policyId\":")
                        .append(rs.getInt("policy_id"))
                        .append(",");

                json.append("\"policyName\":\"")
                        .append(rs.getString("policy_name"))
                        .append("\",");

                json.append("\"policyType\":\"")
                        .append(rs.getString("policy_type"))
                        .append("\",");

                json.append("\"coverageAmount\":")
                        .append(rs.getDouble("coverage_amount"))
                        .append(",");

                json.append("\"premiumAmount\":")
                        .append(rs.getDouble("premium_amount"));

                json.append("}");

                first = false;
            }

            json.append("]");

            out.print(json.toString());

        }

        catch (Exception e) {

            e.printStackTrace();

            out.print("{}");

        }

    }
    

    

}