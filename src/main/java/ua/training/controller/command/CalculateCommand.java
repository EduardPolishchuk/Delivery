package ua.training.controller.command;

import ua.training.model.entity.City;
import ua.training.model.service.CityService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static ua.training.constants.Constants.INDEX_JSP;
import static ua.training.constants.Constants.REDIRECT_HOME;

public class CalculateCommand implements Command {

    private CityService cityService;

    public CalculateCommand(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        long cityFromId = Long.parseLong(request.getParameter("cityFrom"));
        long cityToId = Long.parseLong(request.getParameter("cityTo"));

        if (cityFromId == cityToId) {
            request.getSession().setAttribute("calculatedValue", "SAME CITIES!");
            return REDIRECT_HOME;
        }

        Optional<City> op1 = cityService.findById(cityFromId);
        Optional<City> op2 = cityService.findById(cityToId);

        if (op1.isPresent() && op2.isPresent()) {
            request.getSession().setAttribute("calculatedValue", distFrom(op1.get(), op2.get()) + "km");
        } else {
            request.getSession().setAttribute("calculatedValue", "ERROR: cannot find the city, please, refresh the page");
        }

        return REDIRECT_HOME;
    }

    public static float distFrom(City cityFrom, City cityTo) {
        float lat1 = cityFrom.getLatitude();
        float lat2 = cityTo.getLatitude();
        float lng1 = cityFrom.getLongitude();
        float lng2 = cityTo.getLongitude();

        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (float) (earthRadius * c) / 1000;
    }
}
