package ua.training.model.dao;

import ua.training.model.entity.Order;
import ua.training.model.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDao extends GenericDao<Order>{


    List<Order> findUserOrders(User user);
    List<Order> findUserOrdersWithStatus(User user, Order.OrderStatus status);

    List<Order> findOrdersWithStatus( Order.OrderStatus status);

    boolean changeOrderStatus(User user, long orderId, Order.OrderStatus status);

}
