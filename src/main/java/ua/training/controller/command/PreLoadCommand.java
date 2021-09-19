package ua.training.controller.command;


import ua.training.model.entity.City;
import ua.training.model.entity.Tariff;
import ua.training.model.entity.User;
import ua.training.model.service.CityService;
import ua.training.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static ua.training.constants.Constants.*;

public class PreLoadCommand implements Command {
    private final CityService cityService;
    private final TariffService tariffService;

    public PreLoadCommand(CityService cityService, TariffService tariffService) {
        this.cityService = cityService;
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_PROFILE);
        List<City> list = cityService.findAll();
        tariffService.getTariff().ifPresent(tariff -> request.getSession().setAttribute("tariff", tariff));
        request.getSession().setAttribute("cityList", list);
        if (user == null || !User.Role.USER.equals(user.getRole())) {
            return  INDEX_JSP;
        }
        return  USER_MAIN_JSP;
    }
}
