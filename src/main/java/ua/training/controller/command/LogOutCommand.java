package ua.training.controller.command;

import ua.training.controller.util.ContextUtility;
import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;

import static ua.training.constants.Constants.REDIRECT_HOME;
import static ua.training.constants.Constants.ROLE;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        User.Role role = (User.Role) request.getSession().getAttribute(ROLE);
        if (User.Role.USER.equals(role) || User.Role.ADMIN.equals(role)) {
            ContextUtility.logOutUser(request.getSession());
        }
        return REDIRECT_HOME;
    }
}
