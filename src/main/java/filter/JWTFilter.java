package filter;

import io.jsonwebtoken.Claims;
import utils.JWTUtil;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class JWTFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException,
            ServletException {

        HttpServletRequest req =
                (HttpServletRequest) request;

        HttpServletResponse res =
                (HttpServletResponse) response;

        String path =
                req.getRequestURI();
        
        res.setHeader(
                "Access-Control-Allow-Origin",
                "http://localhost:3000");

        res.setHeader(
                "Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS");

        res.setHeader(
                "Access-Control-Allow-Headers",
                "Authorization, Content-Type");

        if(req.getMethod().equals("OPTIONS")) {

            res.setStatus(
                    HttpServletResponse.SC_OK);

            return;
        }

        // Public URLs
     // Public URLs
        
        if (path.contains("/login")
        		
                || path.contains("/register")
                || path.contains("/verifyRegisterOTP")
                || path.contains("/sendOTP")
                || path.contains("/verifyOTP"))
             
        {

            chain.doFilter(request, response);
            return;
        }
        String authHeader =
                req.getHeader(
                        "Authorization");
        
        System.out.println("========== JWT FILTER ==========");
        System.out.println("URL = " + req.getRequestURI());
        System.out.println("Method = " + req.getMethod());
        System.out.println("Authorization = " + authHeader);

        if(authHeader == null
                || !authHeader.startsWith(
                        "Bearer ")) {

            res.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED);

            res.getWriter().print(
                    "Token Missing");

            return;
        }

        try {

            String token =
                    authHeader.substring(7);

            Claims claims =
                    JWTUtil.validateToken(
                            token);

            req.setAttribute(
                    "username",
                    claims.getSubject());

            req.setAttribute(
                    "role",
                    claims.get(
                            "role"));

            chain.doFilter(
                    request,
                    response);

        } catch(Exception e) {
        	
        	e.printStackTrace();

            res.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED);

            res.getWriter().print(
                    "Invalid Token");
        }
    }
}