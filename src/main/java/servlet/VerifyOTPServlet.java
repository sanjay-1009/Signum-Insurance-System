package servlet;

import dao.UserDAO;
import utils.JWTUtil;
import utils.OTPStore;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

@WebServlet("/verifyOTP")
public class VerifyOTPServlet
        extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setHeader(
                "Access-Control-Allow-Origin",
                "https://insurance-frontend-woad.vercel.app");

        String username =
                request.getParameter(
                        "username");

        String otp =
                request.getParameter(
                        "otp");

        String storedOTP =
                OTPStore.otpMap.get(
                        username);

        response.setContentType(
                "application/json");

        if(storedOTP != null
                && storedOTP.equals(
                        otp)) {

            UserDAO dao =
                    new UserDAO();

            String role =
                    dao.getRole(
                            username);

            int userId =
                    dao.getUserId(
                            username);

            String token =
                    JWTUtil.generateToken(
                            username,
                            role);

            response.getWriter()
                    .print(
                    "{"
                    + "\"status\":\"SUCCESS\","
                    + "\"userId\":"
                    + userId
                    + ","
                    + "\"role\":\""
                    + role
                    + "\","
                    + "\"token\":\""
                    + token
                    + "\""
                    + "}"
                    );

            OTPStore.otpMap.remove(
                    username);

        } else {

            response.getWriter()
                    .print(
                    "{"
                    + "\"status\":\"FAILED\""
                    + "}"
                    );
        }
    }
}