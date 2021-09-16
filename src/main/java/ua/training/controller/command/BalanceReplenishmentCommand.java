package ua.training.controller.command;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

import static ua.training.constants.Constants.*;

public class BalanceReplenishmentCommand implements Command {
    private static final Logger logger = LogManager.getLogger(BalanceReplenishmentCommand.class);
    private final UserService userService;

    public BalanceReplenishmentCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_PROFILE);
        try {
            BigDecimal value = new BigDecimal(request.getParameter(AMOUNT));
            if(value.intValue() < 0){
                throw new NumberFormatException();
            }
            user.setBalance(userService.balanceReplenishment(value, user));
            request.getSession().setAttribute(USER_PROFILE, user);
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, e.getMessage());
            return REDIRECT_HOME;
        }
        return REDIRECT_SUCCESS_JSP;
    }
}
