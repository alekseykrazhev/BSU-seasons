package com.example.lab7.Controller.commands;

import com.example.lab7.exceptions.ConnectionPoolException;
import com.example.lab7.exceptions.DAOException;
import com.example.lab7.model.dao.ApplicationDAO;
import com.example.lab7.model.entity.Application;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class ApplicationsCommand extends Command {
    final String urlPattern = "applications";

    private static void printList(Collection list) {
        for (Object row: list) {
            System.out.println(row);
        }
    }

    @Override
    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
        String errorString = null;
        List<Application> applications = null;

        try {
            String queryDate = request.getParameter("name");

            if(queryDate != null) {
                applications = (new ApplicationDAO()).get(Integer.parseInt(queryDate));
            } else {
                applications = (new ApplicationDAO()).getAll();
            }
        } catch (DAOException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } catch (SQLException | ConnectionPoolException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("applicationsList", applications);

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/view/applications.jsp");
        dispatcher.forward(request, response);
    }
}
