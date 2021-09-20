package ua.training.controller.command;

import ua.training.model.entity.City;
import ua.training.model.entity.Order;
import ua.training.model.entity.User;
import ua.training.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ua.training.constants.Constants.*;

public class OrderListCommand implements Command {

    public static final long RECORDS_PER_PAGE = 5;
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
        String sortBy = request.getParameter(SORT_BY);
        String locale = String.valueOf(request.getSession().getAttribute("locale"));
        long page = 1;
        long noOfPages;
        if (sortBy == null) {
            sortBy = "";
        }
        if (request.getParameter(PAGE) != null && !request.getParameter(PAGE).isEmpty()) {
            page = Long.parseLong(request.getParameter(PAGE));
        }
        if (user.getRole().equals(User.Role.USER)) {
            list = orderService.findUserOrders(user);
            path = USER_USERBASIS_JSP;
        } else {
            list = orderService.findAll();
            path = MANAGER_ORDER_LIST_JSP;
        }
        noOfPages = (long) Math.ceil(list.size() * 1.0 / RECORDS_PER_PAGE);
        request.setAttribute("ordersList", sortOrderList(list, sortBy, locale, page));
        request.setAttribute(NO_OF_PAGES, noOfPages);
        request.setAttribute(CURRENT_PAGE_NUMBER, page);
        request.setAttribute(SORT_BY, sortBy);

        return path;
    }

    private List<Order> sortOrderList(List<Order> list, String sortBy, String locale, long page) {
        boolean reverse = sortBy.contains("Desc");

        return list.stream()
                .sorted(getComparator(sortBy.replaceAll("Desc", ""), locale, reverse))
                .skip((page - 1) * RECORDS_PER_PAGE)
                .limit(RECORDS_PER_PAGE)
                .collect(Collectors.toList());
    }

    private Comparator<Order> getComparator(String sortBy, String locale, boolean reverse) {
        Comparator<Order> comparator;
        switch (sortBy) {
            case "requestDate":
                comparator = Comparator.comparing(Order::getRequestDate);
                break;
            case "type":
                comparator = Comparator.comparing(order -> order.getParcel().getType());
                break;
            case "cityFrom":
                comparator = "uk".equals(locale) ?
                        Comparator.comparing(order -> order.getCityFrom().getNameUk()) :
                        Comparator.comparing(order -> order.getCityFrom().getName());
                break;
            case "cityTo":
                comparator = "uk".equals(locale) ?
                        Comparator.comparing(order -> order.getCityTo().getNameUk()) :
                        Comparator.comparing(order -> order.getCityTo().getName());
                break;
            case "status":
                comparator = Comparator.comparing(Order::getStatus);
                break;
            case "receiveDate":
                comparator = Comparator.comparing(Order::getReceivingDate);
                break;
            default:
                comparator = Comparator.comparing(Order::getId);
                break;
        }

        return reverse ? comparator.reversed() : comparator;
    }
}
