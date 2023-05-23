package com.example.lab7.Controller.commands;

import com.example.lab7.exceptions.ConnectionPoolException;
import com.example.lab7.exceptions.DAOException;
import com.example.lab7.model.dao.WorkPlanDAO;
import com.example.lab7.model.dao.WorkerDAO;
import com.example.lab7.model.entity.WorkPlan;

import com.example.lab7.model.entity.Worker;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class WorkplansCommand extends Command {
    final String urlPattern = "workplans";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        String errorString = null;
        List<WorkPlan> workPlans = null;

        System.out.println("workPlans command executed");

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            String queryDate = request.getParameter("date");
            String clientId = request.getParameter("client");

            workPlans = (new WorkPlanDAO()).getAll();

        } catch (DAOException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } catch (SQLException | ConnectionPoolException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("workplansList", workPlans);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/view/workplans.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException
    {
        String name = request.getParameter("name");
        int id_type = Integer.parseInt(request.getParameter("id_type"));
        int worker_id = Integer.parseInt(request.getParameter("worker_id"));
        try {
            WorkPlan wp = new WorkPlan(worker_id, name, (new WorkerDAO()).get(id_type).get(0), id_type);
            (new WorkPlanDAO()).insert(wp, wp.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/view/mainPage.jsp").forward(request, response);
    }
}
