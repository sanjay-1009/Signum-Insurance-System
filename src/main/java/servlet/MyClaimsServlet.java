package servlet;

import controller.ReportController;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet("/myClaims")
public class MyClaimsServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    ReportController controller =
            new ReportController();

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setHeader(
                "Access-Control-Allow-Origin",
                "*");

        String username =
                request.getParameter(
                        "username");

        ArrayList<HashMap<String,Object>>
                claims =
                new ArrayList<>();

        try {

            ResultSet rs =
                    controller
                    .getClaimsByUser(
                            username);

            while(rs.next()) {

                HashMap<String,Object>
                        claim =
                        new HashMap<>();

                claim.put(
                        "claimId",
                        rs.getInt(
                                "claim_id"));

                claim.put(
                        "policyId",
                        rs.getInt(
                                "policy_id"));

                claim.put(
                        "amount",
                        rs.getDouble(
                                "claim_amount"));

                claim.put(
                        "status",
                        rs.getString(
                                "status"));

                claim.put(
                        "date",
                        rs.getDate(
                                "incident_date"));

                claims.add(claim);
            }

            response.getWriter()
                    .print(
                    new Gson()
                    .toJson(claims));

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}