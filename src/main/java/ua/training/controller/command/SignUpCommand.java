package ua.training.controller.command;

import ua.training.controller.validator.UserValidator;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static ua.training.constants.Constants.*;

public class SignUpCommand implements Command {
    private final UserService userService;
    private final UserValidator validator;

    public SignUpCommand(UserService userService, UserValidator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String result = REDIRECT_SUCCESS_JSP;
        Optional<User> optional = validator.singUpValidation(request);
        if (!optional.isPresent()) {
            return SING_UP_JSP;
        }
        if (!userService.create(optional.get())) {
            result = SING_UP_JSP;
            request.getSession().setAttribute(ERROR, LOGIN_INVALID);
        }
        return result;
    }
}
