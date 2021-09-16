package ua.training.controller.command;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.dao.impl.JDBCUserDao;
import ua.training.model.entity.City;
import ua.training.model.entity.Order;
import ua.training.model.entity.Parcel;
import ua.training.model.entity.User;
import ua.training.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDate;

import static ua.training.constants.Constants.*;


public class MakeOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger(MakeOrderCommand.class);
    private OrderService orderService;

    public MakeOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            User user = (User) request.getSession().getAttribute(USER_PROFILE);
            float height = Float.parseFloat(request.getParameter("height"));
            float width = Float.parseFloat(request.getParameter("width"));
            float length = Float.parseFloat(request.getParameter("length"));
            float weight = Float.parseFloat(request.getParameter("weight"));
            String type = request.getParameter("type");
            long cityToId = Long.parseLong(request.getParameter("cityTo"));
            long cityToFromId = Long.parseLong(request.getParameter("cityFrom"));
            Order order = Order.builder()
                    .requestDate(LocalDate.now())
                    .parcel(Parcel.builder()
                            .type(type)
                            .width(width)
                            .weight(weight)
                            .length(length)
                            .height(height)
                            .build())
                    .userSender(user)
                    .cityTo(City.builder().id(cityToId).build())
                    .cityFrom(City.builder().id(cityToFromId).build())
                    .build();
            System.out.println(orderService.create(order));
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, e.getMessage());
            return USER_MAIN_JSP;
        }

        return REDIRECT_SUCCESS_JSP;
    }
}
