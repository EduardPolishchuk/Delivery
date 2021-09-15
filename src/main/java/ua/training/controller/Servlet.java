package ua.training.controller;


import ua.training.controller.command.*;
import ua.training.controller.validator.UserValidator;
import ua.training.model.service.impl.*;

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

    @Override
    public void init(ServletConfig servletConfig) {
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());
        commands.put("start", new PreLoadCommand(new CityServiceImpl(), new TariffServiceImpl()));
        commands.put("test", new TestCommand());
        commands.put("login", new LoginCommand(new UserServiceImpl()));
        commands.put("singUp", new SignUpCommand(new UserServiceImpl(), new UserValidator()));
        commands.put("calculate", new CalculateCommand(new CityServiceImpl()));
        commands.put("logout", new LogOutCommand());
        commands.put("user/userUpdate", new UpdateUserCommand(new UserServiceImpl(), new UserValidator()));
        commands.put("user/userOrders", new UserOrdersCommand(new OrderServiceImpl()));
        commands.put("user/userReceipts", new UserReceiptsCommand(new ReceiptServiceImpl()));
        commands.put("changeBalance", new BalanceReplenishmentCommand(new UserServiceImpl()));
        commands.put("manager/managerClientList", new ClientListCommand(new UserServiceImpl()));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI().replaceAll(".*/Delivery/", "");
        Command command = commands.getOrDefault(path, new PreLoadCommand(new CityServiceImpl(), new TariffServiceImpl()));
        String page = command.execute(request);
        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", "/Delivery"));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
