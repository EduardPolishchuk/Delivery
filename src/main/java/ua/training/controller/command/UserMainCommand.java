package ua.training.controller.command;

import ua.training.model.service.impl.CityServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class UserMainCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) {

        if(request.getParameter("action").equals("makeOrder")){
            return new MakeOrderCommand().execute(request);
        }
        return new CalculateCommand(new CityServiceImpl()).execute(request);
    }
}
