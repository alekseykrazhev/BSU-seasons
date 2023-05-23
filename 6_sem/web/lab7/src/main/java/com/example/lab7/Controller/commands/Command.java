package com.example.lab7.Controller.commands;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.UUID;


public abstract class Command extends HttpServlet {

    public abstract String getPattern();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        super.service(req, response);

        final String VISIT_COUNT_KEY = "VISIT_COUNT_KEY";
        final String USER_ID_KEY = "USER_ID_KEY";

        HttpSession session = req.getSession(true);
        Integer visitCount = 0;

        if (session.isNew()) {
            session.setAttribute(USER_ID_KEY, UUID.randomUUID().toString());
        } else {
            visitCount = (Integer) session.getAttribute(VISIT_COUNT_KEY);
            visitCount++;
        }

        session.setAttribute(VISIT_COUNT_KEY, visitCount);

        Cookie cookie = new Cookie("cookie", visitCount + session.getLastAccessedTime() + "");
        response.addCookie(cookie);
    }

    public abstract void doGet(HttpServletRequest request,
                               HttpServletResponse response,
                               ServletContext servletContext
    ) throws ServletException, IOException;


    public void doPost(HttpServletRequest request,
                       HttpServletResponse response,
                       ServletContext servletContext
    ) throws ServletException, IOException {
        doGet(request, response, servletContext);
    }
}
