package ua.training.controller.command;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.entity.Order;
import ua.training.model.entity.User;
import ua.training.model.service.OrderService;
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
        try {
            User user = (User) request.getSession().getAttribute(USER_PROFILE);
            long orderId = Long.parseLong(request.getParameter("order"));
            Order order = orderService.findById(orderId).orElseThrow(NumberFormatException::new);
            request.getSession().setAttribute("order", order);
            return User.Role.USER.equals(user.getRole()) ? USER_ORDER_VIEW_JSP :
                    "/manager/managerOrderDetails.jsp";
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            return new UserReceiptsCommand(new ReceiptServiceImpl()).execute(request);
        }
    }
}
