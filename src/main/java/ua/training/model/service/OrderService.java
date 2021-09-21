package ua.training.model.service;

import ua.training.model.entity.Order;
import ua.training.model.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    Optional<Order> findById(long id);

    boolean create(Order order);

    boolean update(Order order);

    List<Order> findAll();


    List<Order> findUserOrders(User user);

    BigDecimal calculateOrderPrice(Order order);

}
