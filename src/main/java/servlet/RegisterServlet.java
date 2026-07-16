package servlet;

import controller.RegisterController;
import dao.RegisterDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    RegisterController controller =
            new RegisterController();

    RegisterDAO dao =
            new RegisterDAO();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException,
            IOException {

        response.setHeader(
                "Access-Control-Allow-Origin",
                "http://localhost:3000");

        response.setHeader(
                "Access-Control-Allow-Credentials",
                "true");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out =
                response.getWriter();

        String username =
                request.getParameter("username");

        String password =
                request.getParameter("password");

        String phone =
                request.getParameter("phone");

        String email =
                request.getParameter("email");

        // Validate fields
        if(username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()
                || phone == null || phone.trim().isEmpty()
                || email == null || email.trim().isEmpty()) {

            out.print(
                    "{\"status\":\"FAILED\",\"message\":\"All fields are required\"}");

            return;
        }

        // Username already exists
        if(dao.usernameExists(username)) {

            out.print(
                    "{\"status\":\"FAILED\",\"message\":\"Username already exists\"}");

            return;

        }

        // Email already exists
        if(dao.emailExists(email)) {

            out.print(
                    "{\"status\":\"FAILED\",\"message\":\"Email already registered\"}");

            return;

        }

        try {

            controller.sendOTP(
                    username,
                    password,
                    phone,
                    email);

            out.print(
                    "{\"status\":\"OTP_SENT\",\"message\":\"OTP sent successfully\"}");

        }

        catch(Exception e) {

            e.printStackTrace();

            response.setStatus(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            out.print(
                    "{\"status\":\"FAILED\",\"message\":\"Unable to send OTP\"}");

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
                "http://localhost:3000");

        response.setHeader(
                "Access-Control-Allow-Headers",
                "Content-Type, Authorization");

        response.setHeader(
                "Access-Control-Allow-Methods",
                "POST, OPTIONS");

        response.setStatus(
                HttpServletResponse.SC_OK);

    }

}