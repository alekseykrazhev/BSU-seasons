package com.example.lab7.Controller;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import com.example.lab7.Controller.commands.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name="default", urlPatterns = "/")
public class HelloServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    public Map<String, Command> commands;

    public HelloServlet() {
        super();
        this.commands = new HashMap<>();
    }

    @Override
    public void init() {
        Command[] commands = {
                new HomeCommand(),
                new AboutCommand(),
                new ApplicationsCommand(),
                new WorkplansCommand(),
                new AuthorizationCommand(),
                new CreateUserCommand(),
        };

        for (Command c : commands) {
            this.commands.put(c.getPattern(), c);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");

        if (command == null) {
            commands.get("home").doGet(request, response, this.getServletContext());
        } else if (commands.containsKey(command)) {
            commands.get(command).doGet(request, response, this.getServletContext());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");

        if (command == null) {
            commands.get("home").doGet(request, response, this.getServletContext());
        } else if (commands.containsKey(command)) {
            commands.get(command).doPost(request, response, this.getServletContext());
        }
    }
}