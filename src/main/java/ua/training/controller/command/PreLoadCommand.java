package ua.training.controller.command;


import ua.training.model.entity.City;
import ua.training.model.entity.Tariff;
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
        List<City> list = cityService.findAll();
        Optional<Tariff> ops = tariffService.getTariff();
        if (ops.isPresent()) {
            request.getSession().setAttribute("tariff", ops.get());
        }
        request.getSession().setAttribute("cityList", list);

        return INDEX_JSP;
    }
}
