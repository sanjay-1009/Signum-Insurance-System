package servlet;

import controller.UserPolicyController;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet("/myPolicy")
public class MyPolicyServlet extends HttpServlet {

    UserPolicyController controller =
            new UserPolicyController();

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setHeader(
                "Access-Control-Allow-Origin",
                "*");

        int userId =
                Integer.parseInt(
                        request.getParameter("userId"));

        ArrayList<HashMap<String,Object>>
                policies =
                new ArrayList<>();

        try {

            ResultSet rs =
                    controller.getPolicy(userId);

            while(rs.next()) {

                HashMap<String,Object>
                        policy =
                        new HashMap<>();
                
                policy.put(
                	    "policyId",
                	    rs.getInt("policy_id")
                	);

                policy.put(
                        "policyName",
                        rs.getString("policy_name"));

                policy.put(
                        "policyType",
                        rs.getString("policy_type"));

                policy.put(
                        "premiumPaid",
                        rs.getDouble("premium_paid"));

                policy.put(
                        "maturityAmount",
                        rs.getDouble("maturity_amount"));

                policy.put(
                        "startDate",
                        rs.getDate("start_date"));

                policy.put(
                        "endDate",
                        rs.getDate("end_date"));

                LocalDate today =
                        LocalDate.now();

                LocalDate end =
                        rs.getDate("end_date")
                                .toLocalDate();

                long daysLeft =
                        ChronoUnit.DAYS
                                .between(today,end);

                policy.put(
                        "daysLeft",
                        daysLeft);

                policies.add(policy);

            }

            response.setContentType(
                    "application/json");

            response.getWriter().print(

                    new Gson().toJson(policies)

            );

        }

        catch(Exception e){

            e.printStackTrace();

        }

    }

}