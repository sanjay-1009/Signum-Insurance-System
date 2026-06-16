package servlet;

import controller.LoginController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    LoginController controller =
            new LoginController();

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    	
    	response.setHeader(
    		    "Access-Control-Allow-Origin",
    		    "*");

        String username =
                request.getParameter("username");

        String password =
                request.getParameter("password");

        String role =
        	    controller.login(
        	        username,
        	        password);

        response.setContentType(
                "application/json");

        PrintWriter out =
                response.getWriter();

        if(role != null) {

            int userId =
                    controller.getUserId(
                            username);

            out.print(
                "{"
                + "\"status\":\"SUCCESS\","
                + "\"role\":\"" + role + "\","
                + "\"userId\":" + userId
                + "}"
            );

        } else {

            out.print(
                "{"
                + "\"status\":\"FAILED\""
                + "}"
            );
        }}}