package ua.training.model.dao;

import ua.training.model.entity.Order;
import ua.training.model.entity.User;

import java.util.List;

public interface OrderDao extends GenericDao<Order>{

    List<Order> findUserOrders(User user);
}
