package ua.training.controller.command.user;

import ua.training.controller.command.CalculateCommand;
import ua.training.controller.command.Command;
import ua.training.model.service.impl.CityServiceImpl;
import ua.training.model.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class UserMainCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        if(request.getParameter("action").equals("makeOrder")){
            return new MakeOrderCommand(new OrderServiceImpl()).execute(request);
        }
        return new CalculateCommand(new CityServiceImpl(), new OrderServiceImpl()).execute(request);
    }
}
