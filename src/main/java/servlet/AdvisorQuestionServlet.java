package servlet;

import controller.AdvisorQuestionController;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

@WebServlet("/advisorQuestions")
public class AdvisorQuestionServlet
extends HttpServlet {

    private static final long serialVersionUID = 1L;

    AdvisorQuestionController controller =
            new AdvisorQuestionController();

    protected void doGet(

            HttpServletRequest request,

            HttpServletResponse response)

            throws IOException {

        response.setHeader(
                "Access-Control-Allow-Origin",
                "http://localhost:3000");

        response.setContentType(
                "application/json");

        PrintWriter out =
                response.getWriter();

        try {

            String insuranceType =
                    request.getParameter(
                            "insuranceType");

            ResultSet rs =
                    controller.getQuestions(
                            insuranceType);

            StringBuilder json =
                    new StringBuilder();

            json.append("[");

            boolean first = true;

            while(rs.next()) {

                if(!first) {

                    json.append(",");

                }

                json.append("{");

                json.append("\"questionId\":")
                        .append(rs.getInt("question_id"))
                        .append(",");

                json.append("\"question\":\"")
                        .append(rs.getString("question"))
                        .append("\",");

                json.append("\"inputType\":\"")
                        .append(rs.getString("input_type"))
                        .append("\",");

                json.append("\"options\":\"")
                        .append(rs.getString("options"))
                        .append("\"");

                json.append("}");

                first = false;

            }

            json.append("]");

            out.print(json.toString());

        }

        catch(Exception e) {

            e.printStackTrace();

            out.print("[]");

        }

    }

}