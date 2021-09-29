package ua.training.controller.command.user;

import ua.training.controller.command.CalculateCommand;
import ua.training.controller.command.Command;
import ua.training.controller.command.PreLoadCommand;
import ua.training.model.service.impl.CityServiceImpl;
import ua.training.model.service.impl.OrderServiceImpl;
import ua.training.model.service.impl.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class UserMainCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String cityToId = request.getParameter("cityFrom");
        String cityFromId = request.getParameter("cityTo");
        if (cityFromId != null && cityFromId.equals(cityToId)){
            request.getSession().setAttribute("error", "sameCity");
            return new PreLoadCommand(new CityServiceImpl(), new TariffServiceImpl()).execute(request);
        }

        if (request.getParameter("action").equals("makeOrder")) {
            return new MakeOrderCommand(new OrderServiceImpl()).execute(request);
        }
        return new CalculateCommand(new CityServiceImpl(), new OrderServiceImpl()).execute(request);
    }
}
