package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;
import static ua.training.constants.Constants.*;


public class MakeOrderCommand implements Command{


    @Override
    public String execute(HttpServletRequest request) {


        return USER_MAIN_JSP;
    }
}
