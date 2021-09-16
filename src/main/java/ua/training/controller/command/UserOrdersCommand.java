package ua.training.controller.command;

import ua.training.model.entity.Order;
import ua.training.model.entity.User;
import ua.training.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static ua.training.constants.Constants.*;

public class UserOrdersCommand implements Command{

    public static final int RECORDS_PER_PAGE = 3;
    public static final String CURRENT_PAGE_NUMBER = "currentPage";
    private final OrderService orderService;

    public UserOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userProfile");
        long page = 1;
        String sortBy = request.getParameter(SORT_BY);
        if (sortBy == null ) {
            sortBy = "";
        }
        if (request.getParameter(PAGE) != null && !request.getParameter(PAGE).isEmpty()) {
            page = Long.parseLong(request.getParameter(PAGE));
        }
        List<Order> list = orderService.findSortedUserOrdersFromIndex(user, sortBy, (page - 1) * RECORDS_PER_PAGE,
                RECORDS_PER_PAGE);

        long noOfRecords = orderService.findUserOrdersAmount(user);
        long noOfPages = (long) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);
        request.setAttribute("userOrders", list);
        request.setAttribute(NO_OF_PAGES, noOfPages);
        request.setAttribute(CURRENT_PAGE_NUMBER, page);
//        request.setAttribute(SORT_BY, sortBy);

        return USER_USERBASIS_JSP;
    }
}
