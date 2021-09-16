package ua.training.controller.command;

import ua.training.model.service.OrderService;
import javax.servlet.http.HttpServletRequest;
import static ua.training.constants.Constants.*;

public class OrderViewCommand implements Command{

    private OrderService orderService;

    public OrderViewCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return USER_ORDER_VIEW_JSP;
    }
}
