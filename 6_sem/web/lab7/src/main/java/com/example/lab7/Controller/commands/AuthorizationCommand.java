package com.example.lab7.Controller.commands;

import com.example.lab7.exceptions.ConnectionPoolException;
import com.example.lab7.exceptions.DAOException;
import com.example.lab7.model.dao.DAOUser;
import com.example.lab7.model.entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

public class AuthorizationCommand extends Command {
    final String urlPattern = "authorization";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/view/authorization.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = (new DAOUser()).loginUser(username, password);

        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            response.sendRedirect("/view/index.jsp");
        } else {
            String errorString = "Invalid credentials";
            request.setAttribute("errorString", errorString);
            response.sendRedirect("/view/authorization.jsp");
        }
    }

    private boolean authenticate(String username, String password) {
        return username.equals("admin") && password.equals("password");
    }
}