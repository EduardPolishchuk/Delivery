package ua.training.controller.command;

import ua.training.model.entity.Order;
import ua.training.model.entity.User;
import ua.training.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static ua.training.constants.Constants.*;

public class OrderListCommand implements Command{

    public static final int RECORDS_PER_PAGE = 4;
    public static final String CURRENT_PAGE_NUMBER = "currentPage";
    private final OrderService orderService;

    public OrderListCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userProfile");
        List<Order> list;
        String path;
        long page = 1;
        long noOfRecords;
        long noOfPages;
        String sortBy = request.getParameter(SORT_BY);
        if (sortBy == null ) {
            sortBy = "";
        }
        if (request.getParameter(PAGE) != null && !request.getParameter(PAGE).isEmpty()) {
            page = Long.parseLong(request.getParameter(PAGE));
        }
        if (user.getRole().equals(User.Role.USER)){
             list = orderService.findSortedUserOrdersFromIndex(user, sortBy, (page - 1) * RECORDS_PER_PAGE,
                    RECORDS_PER_PAGE);
            noOfRecords = orderService.findUserOrdersAmount(user);
            path = USER_USERBASIS_JSP;
        }else {
            list = orderService.findSortedOrdersFromIndex(sortBy, (page - 1) * RECORDS_PER_PAGE,
                    RECORDS_PER_PAGE);
            noOfRecords = orderService.findOrdersForConfirmAmount();
            path = MANAGER_ORDER_LIST_JSP;
        }
        noOfPages = (long) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);
        request.setAttribute("userOrders", list);
        request.setAttribute(NO_OF_PAGES, noOfPages);
        request.setAttribute(CURRENT_PAGE_NUMBER, page);
        request.setAttribute(SORT_BY, sortBy);

        return path;
    }
}
