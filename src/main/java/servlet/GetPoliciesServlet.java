package servlet;

import controller.PolicyController;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet("/getPolicies")
public class GetPoliciesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    PolicyController controller =
            new PolicyController();

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setHeader(
                "Access-Control-Allow-Origin",
                "*");

        response.setContentType(
                "application/json");

        ArrayList<HashMap<String,Object>>
                policies =
                new ArrayList<>();

        try {

            ResultSet rs =
                    controller.getAllPolicies();

            while(rs.next()) {

                HashMap<String,Object>
                        policy =
                        new HashMap<>();

                policy.put(
                        "policyId",
                        rs.getInt("policy_id"));

                policy.put(
                        "policyName",
                        rs.getString("policy_name"));

                policy.put(
                        "policyType",
                        rs.getString("policy_type"));

                policy.put(
                        "coverageAmount",
                        rs.getDouble("coverage_amount"));

                policy.put(
                        "premiumAmount",
                        rs.getDouble("premium_amount"));

                policies.add(policy);
            }

            response.getWriter().print(
                    new Gson().toJson(
                            policies));

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}