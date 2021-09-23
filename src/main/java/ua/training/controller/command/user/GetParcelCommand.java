package ua.training.controller.command.user;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.OrderListCommand;
import ua.training.model.entity.Order;
import ua.training.model.entity.User;
import ua.training.model.service.OrderService;
import ua.training.model.service.impl.OrderServiceImpl;

import static ua.training.constants.Constants.*;


import javax.servlet.http.HttpServletRequest;

public class GetParcelCommand implements Command {

    private final OrderService orderService;
    private static final Logger LOGGER = LogManager.getLogger(GetParcelCommand.class);

    public GetParcelCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            long orderId = Long.parseLong(request.getParameter("order_id"));
            orderService.changeOrderStatus((User) request.getSession().getAttribute(USER_PROFILE)
                    ,orderId, Order.OrderStatus.DELIVERED);
        }catch (NumberFormatException e){
            LOGGER.log(Level.ERROR, e.getMessage());
        }
        return new OrderListCommand(new OrderServiceImpl()).execute(request);
    }
}
