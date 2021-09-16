package ua.training.controller.command;

import ua.training.model.entity.City;
import ua.training.model.entity.Order;
import ua.training.model.entity.Parcel;
import ua.training.model.entity.User;
import ua.training.model.service.CityService;
import ua.training.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static ua.training.constants.Constants.*;

public class CalculateCommand implements Command {

    private CityService cityService;
    private OrderService orderService;

    public CalculateCommand(CityService cityService, OrderService orderService) {
        this.cityService = cityService;
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_PROFILE);
        String returnPage;
        if (user == null || !User.Role.USER.equals(user.getRole())) {
            returnPage = INDEX_JSP;
        } else {
            returnPage = USER_MAIN_JSP;
        }
        long cityFromId = Long.parseLong(request.getParameter("cityFrom"));
        long cityToId = Long.parseLong(request.getParameter("cityTo"));

        request.setAttribute("cityFrom", cityFromId);
        request.setAttribute("cityTo", cityFromId);

        if (cityFromId == cityToId) {
            request.getSession().setAttribute("calculatedValue", "SAME CITIES!");
            return returnPage;
        }


        Optional<City> optionalCityFrom = cityService.findById(cityFromId);
        Optional<City> optionalCityTo = cityService.findById(cityToId);

        if (optionalCityFrom.isPresent() && optionalCityTo.isPresent()) {
            float length = Float.parseFloat(request.getParameter("length"));
            float width = Float.parseFloat(request.getParameter("width"));
            float height = Float.parseFloat(request.getParameter("height"));
            float weight = Float.parseFloat(request.getParameter("length"));
            Order order = Order.builder()
                    .parcel(Parcel.builder()
                            .height(height)
                            .length(length)
                            .weight(weight)
                            .width(width)
                            .build())
                    .cityTo(optionalCityTo.get())
                    .cityFrom(optionalCityFrom.get())
                    .build();
            request.getSession().setAttribute("calculatedValue", orderService.calculateOrderPrice(order));
        } else {
            request.getSession().setAttribute("calculatedValue", "ERROR: cannot find the city, please, refresh the page");
        }

        return returnPage;
    }

}
