package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import controller.AdvisorRecommendController;

import model.AdvisorAnswer;
import model.AdvisorRequest;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.net.HttpURLConnection;
import java.net.URL;

import java.nio.charset.StandardCharsets;

import java.sql.ResultSet;

@WebServlet("/advisorRecommend")
public class AdvisorRecommendServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    AdvisorRecommendController controller =
            new AdvisorRecommendController();

    @Override
    protected void doPost(

            HttpServletRequest request,

            HttpServletResponse response)

            throws IOException {

        response.setHeader(
                "Access-Control-Allow-Origin",
                "https://insurance-frontend-woad.vercel.app");

        response.setCharacterEncoding("UTF-8");

        response.setContentType(
                "application/json;charset=UTF-8");

        Gson gson = new Gson();

        BufferedReader reader =
                request.getReader();

        AdvisorRequest advisorRequest =
                gson.fromJson(
                        reader,
                        AdvisorRequest.class);

        // ===========================================
        // Fetch Matching Policies
        // ===========================================

        StringBuilder policies =
                new StringBuilder();

        try {

            ResultSet rs =
                    controller.getPolicies(
                            advisorRequest.getInsuranceType());

            while (rs.next()) {
            	
            	 System.out.println(
            	            "Policy : "
            	            + rs.getString("policy_name"));


            	policies.append("=====================================\n");
            

            	policies.append("Policy Name : ")
            	        .append(rs.getString("policy_name"))

            	        .append("\nPolicy Type : ")
            	        .append(rs.getString("policy_type"))

            	        .append("\nCoverage Amount : ₹")
            	        .append(rs.getDouble("coverage_amount"))

            	        .append("\nPremium Amount : ₹")
            	        .append(rs.getDouble("premium_amount"))

            	        .append("\nEligibility : ")
            	        .append(rs.getString("eligibility"))

            	        .append("\nBenefits : ")
            	        .append(rs.getString("benefits"))

            	        .append("\nExclusions : ")
            	        .append(rs.getString("exclusions"))

            	        .append("\nCovered Diseases : ")
            	        .append(rs.getString("covered_diseases"))

            	        .append("\nWaiting Period : ")
            	        .append(rs.getString("waiting_period"))

            	        .append("\nRequired Documents : ")
            	        .append(rs.getString("required_documents"))

            	        .append("\nClaim Process : ")
            	        .append(rs.getString("claim_process"))

            	        .append("\nSuitable For : ")
            	        .append(rs.getString("suitable_for"))

            	        .append("\nAI Summary : ")
            	        .append(rs.getString("ai_summary"))

            	        .append("\n=====================================\n\n");

            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        // ===========================================
        // Build AI Prompt
        // ===========================================

        StringBuilder prompt =
                new StringBuilder();

        prompt.append("""

        		You are Signum AI Insurance Advisor.

        		You are given a list of insurance policies from the company's database.

        		IMPORTANT RULES

        		1. Recommend ONLY ONE policy.

        		2. Choose ONLY from the policies listed below.

        		3. Never invent a policy name.

        		4. Never invent a premium amount.

        		5. Never invent a coverage amount.

        		6. Never invent benefits.

        		7. Compare all available policies using:

        		• Coverage
        		• Premium
        		• Eligibility
        		• Benefits
        		• Waiting Period
        		• Exclusions
        		• Suitable For

        		8. Compare those policies with the customer's answers.

        		9. Explain why the selected policy is the best.

        		10. Briefly explain why the remaining policies are less suitable.

        		11. If no policy satisfies the customer's needs, reply:

        		"No suitable policy available."

        		Return the answer in this format.

        		Recommended Policy

        		Why Recommended

        		Coverage Amount

        		Premium Amount

        		Key Benefits

        		Comparison With Other Policies

        		Final Recommendation

        		Use professional business language.

        		Do NOT use emojis.

        		""");

        prompt.append(
                "Available Insurance Policies:\n\n");

        prompt.append(
                policies.toString());

        prompt.append(
                "\nCustomer Answers:\n\n");

        if (advisorRequest.getAnswers() != null) {

            for (AdvisorAnswer answer :
                    advisorRequest.getAnswers()) {

                prompt.append(answer.getQuestion())

                        .append(" : ")

                        .append(answer.getAnswer())

                        .append("\n");

            }

        }

        prompt.append("""

Based on the above customer requirements,

Recommend ONLY ONE best insurance policy.

Your answer should contain:

1. Recommended Policy Name

2. Why this policy suits the customer

3. Coverage Amount

4. Premium Amount

5. Advantages

6. Any important recommendation

Keep the response professional and easy to understand.

""");

        // ===========================================
        // Call Spring Boot AI Service
        // ===========================================

        String aiReply =
                "{\"reply\":\"Unable to generate recommendation.\"}";

        try {

            URL url =
                    new URL(
                            "https://signum-ai-service.onrender.com/ai/chat");

            HttpURLConnection connection =
                    (HttpURLConnection)
                            url.openConnection();

            connection.setRequestMethod("POST");

            connection.setDoOutput(true);

            connection.setRequestProperty(
                    "Content-Type",
                    "application/json; charset=UTF-8");

            connection.setRequestProperty(
                    "Accept",
                    "application/json");

            JsonObject body =
                    new JsonObject();

            body.addProperty(
                    "message",
                    prompt.toString());

            body.addProperty(
                    "userId",
                    advisorRequest.getUserId());

            OutputStream os =
                    connection.getOutputStream();

            os.write(

                    body.toString()

                            .getBytes(StandardCharsets.UTF_8)

            );

            os.flush();

            os.close();

            BufferedReader br =
                    new BufferedReader(

                            new InputStreamReader(

                                    connection.getInputStream(),

                                    StandardCharsets.UTF_8

                            )

                    );

            StringBuilder result =
                    new StringBuilder();

            String line;

            while ((line = br.readLine()) != null) {

                result.append(line);

            }

            br.close();

            aiReply =
                    result.toString();

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        // ===========================================
        // Return Response
        // ===========================================

        PrintWriter out =
                response.getWriter();

        out.print(aiReply);

        out.flush();

    }

}