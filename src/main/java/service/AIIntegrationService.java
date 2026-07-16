package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AIIntegrationService {

    private static final String AI_URL =
            "http://localhost:8081/ai/analyze-policy";

    public String analyzePolicy(String policyText) {

        try {

            URL url = new URL(AI_URL);

            HttpURLConnection con =
                    (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");

            con.setRequestProperty(
                    "Content-Type",
                    "application/json");

            con.setDoOutput(true);

            String json =
                    "{\"policyText\":\""
                    + escape(policyText)
                    + "\"}";

            OutputStream os =
                    con.getOutputStream();

            os.write(json.getBytes());

            os.flush();

            os.close();

            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(
                                    con.getInputStream()));

            StringBuilder response =
                    new StringBuilder();

            String line;

            while ((line = br.readLine()) != null) {

                response.append(line);

            }

            br.close();

            return response.toString();

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    private String escape(String text) {

        return text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "");

    }

}