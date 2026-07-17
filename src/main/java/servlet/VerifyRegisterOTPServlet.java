package servlet;

import dao.RegisterDAO;
import model.User;
import utils.OTPManager;
import utils.OTPStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/verifyRegisterOTP")
public class VerifyRegisterOTPServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    RegisterDAO dao = new RegisterDAO();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException,
            IOException {

        response.setHeader(
                "Access-Control-Allow-Origin",
                "https://insurance-frontend-woad.vercel.app");

        response.setHeader(
                "Access-Control-Allow-Credentials",
                "true");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out =
                response.getWriter();

        String email =
                request.getParameter("email");

        String otp =
                request.getParameter("otp");

        if(email == null || otp == null) {

            out.print(
                    "{\"status\":\"FAILED\",\"message\":\"Invalid Request\"}");

            return;
        }

        if(!OTPManager.verifyOTP(email, otp)) {

            out.print(
                    "{\"status\":\"FAILED\",\"message\":\"Invalid OTP\"}");

            return;
        }

        String[] data =
                OTPStore.userMap.get(email);

        if(data == null) {

            out.print(
                    "{\"status\":\"FAILED\",\"message\":\"Session Expired\"}");

            return;
        }

        User user =
                new User();

        user.setUsername(data[0]);
        user.setPassword(data[1]);
        user.setPhone(data[2]);
        user.setEmail(email);

        boolean success =
                dao.register(user);

        if(success) {

            OTPManager.removeOTP(email);

            OTPStore.userMap.remove(email);

            out.print(
                    "{\"status\":\"SUCCESS\"}");

        } else {

            out.print(
                    "{\"status\":\"FAILED\",\"message\":\"Registration Failed\"}");

        }

    }

    @Override
    protected void doOptions(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException,
            IOException {

        response.setHeader(
                "Access-Control-Allow-Origin",
                "https://insurance-frontend-woad.vercel.app");

        response.setHeader(
                "Access-Control-Allow-Headers",
                "Content-Type");

        response.setHeader(
                "Access-Control-Allow-Methods",
                "POST,OPTIONS");

        response.setStatus(
                HttpServletResponse.SC_OK);
    }

}