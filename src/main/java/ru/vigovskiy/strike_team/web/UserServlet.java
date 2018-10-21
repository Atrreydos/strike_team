package ru.vigovskiy.strike_team.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users")
public class UserServlet extends javax.servlet.http.HttpServlet {
    
    private static final Logger log = LoggerFactory.getLogger(UserServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirecting to users.jsp");

        //        request.getRequestDispatcher("/users.jsp").forward(request, response);
        response.sendRedirect("users.jsp");
    }
}
