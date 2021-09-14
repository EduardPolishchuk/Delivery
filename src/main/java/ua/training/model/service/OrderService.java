package ua.training.model.service;

import ua.training.model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Optional<Order> findById(long id);

    boolean create(Order order);

    boolean update(Order order);

    List<Order> findAll();
}
