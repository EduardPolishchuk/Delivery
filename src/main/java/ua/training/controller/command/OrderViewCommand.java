package ua.training.controller.command;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.entity.Order;
import ua.training.model.entity.User;
import ua.training.model.service.OrderService;
import ua.training.model.service.impl.OrderServiceImpl;
import ua.training.model.service.impl.ReceiptServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static ua.training.constants.Constants.USER_ORDER_VIEW_JSP;
import static ua.training.constants.Constants.USER_PROFILE;

public class OrderViewCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(OrderViewCommand.class);
    private OrderService orderService;

    public OrderViewCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_PROFILE);
        boolean isManager = User.Role.MANAGER.equals(user.getRole());
        try {
            long orderId = Long.parseLong(request.getParameter("order"));
            Order order = orderService.findById(orderId).orElseThrow(NumberFormatException::new);
            request.getSession().setAttribute("order", order);
            request.getSession().setAttribute("price", orderService.calculateOrderPrice(order));
            return isManager ? "/manager/managerOrderDetails.jsp" : USER_ORDER_VIEW_JSP;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            return isManager ? new OrderListCommand(new OrderServiceImpl()).execute(request) :
                    new UserReceiptsCommand(new ReceiptServiceImpl()).execute(request);
        }
    }
}
