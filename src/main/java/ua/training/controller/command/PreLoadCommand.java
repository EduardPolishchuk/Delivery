package ua.training.controller.command;


import ua.training.model.entity.City;
import ua.training.model.service.CityService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.training.constants.Constants.*;

public class PreLoadCommand implements Command {
    private static final int RECORDS_PER_PAGE = 3;
    private static final String CURRENT_PAGE_NUMBER = "currentPage";
    private final CityService cityService;

    public PreLoadCommand(CityService cityService) {
        this.cityService = cityService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        List<City> list = cityService.findAll();
        request.setAttribute("cityList", list);
        return INDEX_JSP;
    }
}
