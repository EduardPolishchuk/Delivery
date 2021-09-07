package ua.training.controller;


import ua.training.controller.command.*;
import ua.training.controller.validator.UserValidator;
import ua.training.model.service.impl.CityServiceImpl;
import ua.training.model.service.impl.UserServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends HttpServlet {
    private final Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());
        commands.put("start", new PreLoadCommand(new CityServiceImpl()));
        commands.put("test", new TestCommand());
        commands.put("login", new LoginCommand(new UserServiceImpl()));
        commands.put("singUp", new SignUpCommand(new UserServiceImpl(), new UserValidator()));
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI().replaceAll(".*/Delivery/" , "");
        Command command = commands.getOrDefault(path , new PreLoadCommand(new CityServiceImpl()));
        String page = command.execute(request);
        if(page.contains("redirect:")){
            response.sendRedirect(page.replace("redirect:", "/Delivery"));
        }
        else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
