package com.example.lab7.Controller.commands;

import com.example.lab7.model.dao.DAOUser;
import com.example.lab7.model.entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class CreateUserCommand extends Command {
    final String urlPattern = "signup";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/view/signup.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isChecked = Boolean.parseBoolean(request.getParameter("checked"));
        String role = "user";

        if (isChecked){
            role = "admin";
        }

        User user = new User(username, password, role);

        boolean res = (new DAOUser()).createUser(user);

        if (res) {
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
