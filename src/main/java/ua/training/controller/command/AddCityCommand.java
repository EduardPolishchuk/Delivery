package ua.training.controller.command;

import ua.training.model.service.CityService;

import javax.servlet.http.HttpServletRequest;

public class AddCityCommand implements Command{

    private CityService cityService;

    public AddCityCommand(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
