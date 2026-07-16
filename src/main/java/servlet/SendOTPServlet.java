package servlet;

import utils.OTPStore;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

@WebServlet("/sendOTP")
public class SendOTPServlet
        extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setHeader(
                "Access-Control-Allow-Origin",
                "http://localhost:3000");

        String username =
                request.getParameter(
                        "username");

        String phone =
                request.getParameter(
                        "phone");

        String otp =
                String.valueOf(
                        100000 +
                        new Random()
                                .nextInt(900000));

        OTPStore.otpMap.put(
                username,
                otp);

        try {

            String apiKey =
                    "DZcNt6eERmbsQwnFKgxo4YXOPCS80L1IGaqkrdT7hil2BvH9MyDwTcGSHv8Vl5kbY9y0BgJhM6x1qRpr";

            String message =
                    URLEncoder.encode(
                            "Your OTP is "
                                    + otp,
                            "UTF-8");

            String url =
                    "https://www.fast2sms.com/dev/bulkV2"
                    + "?authorization="
                    + apiKey
                    + "&route=q"
                    + "&message="
                    + message
                    + "&language=english"
                    + "&flash=0"
                    + "&numbers="
                    + phone;

            URL obj =
                    new URL(url);

            HttpURLConnection con =
                    (HttpURLConnection)
                            obj.openConnection();

            con.setRequestMethod(
                    "GET");

            int responseCode =
                    con.getResponseCode();

            System.out.println(
                    "OTP Sent: "
                    + otp);

            System.out.println(
                    "Response Code: "
                    + responseCode);

            response.getWriter()
                    .print("OTP Sent");

        } catch(Exception e) {

            e.printStackTrace();

            response.getWriter()
                    .print(
                            "OTP Failed");
        }
    }
}