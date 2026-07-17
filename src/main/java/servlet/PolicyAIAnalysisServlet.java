package servlet;

import service.AIIntegrationService;
import service.PDFExtractorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.File;
import java.io.IOException;

@WebServlet("/policy/analyze")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 20 * 1024 * 1024,
        maxRequestSize = 25 * 1024 * 1024
)
public class PolicyAIAnalysisServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    PDFExtractorService pdfService =
            new PDFExtractorService();

    AIIntegrationService aiService =
            new AIIntegrationService();

    @Override
    protected void doOptions(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setHeader(
                "Access-Control-Allow-Origin",
                "https://insurance-frontend-woad.vercel.app");

        response.setHeader(
                "Access-Control-Allow-Methods",
                "POST,OPTIONS");

        response.setHeader(
                "Access-Control-Allow-Headers",
                "*");

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader(
                "Access-Control-Allow-Origin",
                "https://insurance-frontend-woad.vercel.app");

        response.setContentType(
                "application/json");

        File pdfFile = null;

        try {

            Part pdfPart =
                    request.getPart("policyPdf");

            String uploadDir =
                    System.getProperty("java.io.tmpdir");

            pdfFile =
                    new File(
                            uploadDir,
                            pdfPart.getSubmittedFileName());

            pdfPart.write(
                    pdfFile.getAbsolutePath());

            String pdfText =
                    pdfService.extractText(
                            pdfFile.getAbsolutePath());

            if(pdfText == null || pdfText.trim().isEmpty()){

                response.setStatus(400);

                response.getWriter().print(
                        "{\"error\":\"Unable to extract text from PDF.\"}");

                return;

            }

            String aiResult =
                    aiService.analyzePolicy(
                            pdfText);

            response.getWriter().print(
                    aiResult);

        }

        catch(Exception e){

            e.printStackTrace();

            response.setStatus(500);

            response.getWriter().print(
                    "{\"error\":\"AI Analysis Failed\"}");

        }

        finally {

            if(pdfFile != null && pdfFile.exists()){

                pdfFile.delete();

            }

        }

    }

}