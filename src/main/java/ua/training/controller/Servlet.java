package ua.training.controller;


import ua.training.controller.command.Command;

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
//        String path = request.getRequestURI().replaceAll(".*/Exhibition/" , "");
//        Command command = commands.getOrDefault(path , new PreLoadCommand(new ExhibitionService()));
//        String page = command.execute(request);
//        if(page.contains("redirect:")){
//            response.sendRedirect(page.replace("redirect:", "/Exhibition"));
//        }
//        else {
//            request.getRequestDispatcher(page).forward(request, response);
//        }
    }
}
