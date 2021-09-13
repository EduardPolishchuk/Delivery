package ua.training.controller.command;

import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static ua.training.constants.Constants.CLIENT_LIST_JSP;
import static ua.training.constants.Constants.USER_LIST;

public class ClientListCommand implements Command {
    private final UserService userService;

    public ClientListCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute(USER_LIST, userService.findAll());
        return CLIENT_LIST_JSP;
    }
}
