package ua.training.model.service;

import ua.training.model.entity.Order;
import ua.training.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Optional<Order> findById(long id);

    boolean create(Order order);

    boolean update(Order order);

    List<Order> findAll();

    List<Order> findSortedUserOrdersFromIndex(User user, String sortBy, long startIndex, int amount);

    List<Order> findSortedOrdersFromIndex( String sortBy, long startIndex, int amount);

    long findOrdersForConfirmAmount();

    long findUserOrdersAmount(User user);
}
