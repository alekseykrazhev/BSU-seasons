package com.example.lab7.Controller.commands;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeCommand extends Command {
    final String urlPattern = "home";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/view/index.jsp");
        dispatcher.forward(request, response);
    }
}
