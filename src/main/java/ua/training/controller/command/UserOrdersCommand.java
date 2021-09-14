package ua.training.controller.command;

import ua.training.model.entity.Order;
import ua.training.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static ua.training.constants.Constants.*;

public class UserOrdersCommand implements Command{

    private final OrderService orderService;

    public UserOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("userOrders" ,orderService.findAll());
        return USER_USERBASIS_JSP;
    }
}
