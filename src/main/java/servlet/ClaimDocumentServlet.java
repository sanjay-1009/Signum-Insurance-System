package servlet;

import controller.ClaimDocumentController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

@WebServlet("/claimDocuments")
public class ClaimDocumentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    ClaimDocumentController controller =
            new ClaimDocumentController();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader(
                "Access-Control-Allow-Origin",
                "https://insurance-frontend-woad.vercel.app");

        response.setContentType("application/json");

        PrintWriter out =
                response.getWriter();

        try {

            int claimId =
                    Integer.parseInt(
                            request.getParameter("claimId"));

            ResultSet rs =
                    controller.getDocumentsByClaimId(
                            claimId);

            StringBuilder json =
                    new StringBuilder();

            json.append("[");

            boolean first = true;

            while(rs.next()){

                if(!first){

                    json.append(",");

                }

                json.append("{");

                json.append("\"documentId\":")
                        .append(rs.getInt("document_id"))
                        .append(",");

                json.append("\"documentName\":\"")
                        .append(rs.getString("document_name"))
                        .append("\",");

                json.append("\"documentUrl\":\"")
                        .append(rs.getString("document_url"))
                        .append("\"");

                json.append("}");

                first = false;

            }

            json.append("]");

            out.print(json.toString());

        }

        catch(Exception e){

            e.printStackTrace();

            out.print("[]");

        }

    }

}