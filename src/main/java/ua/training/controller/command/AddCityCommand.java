package ua.training.controller.command;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.training.model.entity.City;
import ua.training.model.service.CityService;

import javax.servlet.http.HttpServletRequest;

import static ua.training.constants.Constants.*;

public class AddCityCommand implements Command {

    private final CityService cityService;
    public static final Logger LOGGER = Logger.getLogger(AddCityCommand.class);

    public AddCityCommand(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (!"add".equals(request.getParameter("action"))) {
            return "/manager/managerAddCity.jsp";
        }
        try {
            float latDeg = Float.parseFloat(request.getParameter("latDeg"));
            float latMin = Float.parseFloat(request.getParameter("latMin"));
            float latSec = Float.parseFloat(request.getParameter("latSec"));
            float lngDeg = Float.parseFloat(request.getParameter("lngDeg"));
            float lngMin = Float.parseFloat(request.getParameter("lngMin"));
            float lngSec = Float.parseFloat(request.getParameter("lngSec"));
            String name = request.getParameter("name");
            String nameUk = request.getParameter("nameUk");
            float latDecimalDeg = cityService
                    .convertToDecimalDegrees(latDeg, latMin, latSec);
            float lngDecimalDeg = cityService
                    .convertToDecimalDegrees(lngDeg, lngMin, lngSec);

            latDecimalDeg = "north".equals(request.getParameter("latParam")) ?
                    latDecimalDeg : -1 * latDecimalDeg;

            lngDecimalDeg = "east".equals(request.getParameter("lngParam")) ?
                    lngDecimalDeg : -1 * lngDecimalDeg;

            cityService.create(City.builder()
                    .nameUk(nameUk)
                    .name(name)
                    .latitude(latDecimalDeg)
                    .longitude(lngDecimalDeg)
                    .build());
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            return "/manager/managerAddCity.jsp";
        }
        return REDIRECT_SUCCESS_JSP;
    }
}
