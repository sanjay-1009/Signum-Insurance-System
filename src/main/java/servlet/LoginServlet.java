package servlet;

import controller.LoginController;
import utils.JWTUtil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    LoginController controller = new LoginController();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader(
                "Access-Control-Allow-Origin",
                "http://localhost:3000");

        response.setHeader(
                "Access-Control-Allow-Credentials",
                "true");

        response.setContentType("application/json");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String role = controller.login(username, password);

        PrintWriter out = response.getWriter();

        if (role != null) {

            int userId = controller.getUserId(username);

            String token = JWTUtil.generateToken(
                    username,
                    role);

            out.print(
                    "{"
                    + "\"status\":\"SUCCESS\","
                    + "\"token\":\"" + token + "\","
                    + "\"role\":\"" + role + "\","
                    + "\"userId\":" + userId
                    + "}"
            );

        } else {

            out.print(
                    "{\"status\":\"FAILED\"}"
            );

        }
    }
}