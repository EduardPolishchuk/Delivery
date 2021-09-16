package ua.training.model.service.impl;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.OrderDao;
import ua.training.model.entity.Order;
import ua.training.model.entity.User;
import ua.training.model.service.OrderService;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public Optional<Order> findById(long id) {
       try(OrderDao orderDao = daoFactory.createOrderDao()){
           return orderDao.findById(id);
       }
    }

    @Override
    public boolean create(Order order) {
        try(OrderDao orderDao = daoFactory.createOrderDao()){
            return orderDao.create(order);
        }
    }

    @Override
    public boolean update(Order order) {
        try(OrderDao orderDao = daoFactory.createOrderDao()){
            return orderDao.update(order);
        }
    }

    @Override
    public List<Order> findAll() {
        try(OrderDao orderDao = daoFactory.createOrderDao()){
            return orderDao.findAll();
        }
    }

    @Override
    public List<Order> findSortedUserOrdersFromIndex(User user, String sortBy, long startIndex, int amount) {
        try(OrderDao orderDao = daoFactory.createOrderDao()){
            return orderDao.findSortedUserOrdersFromIndex(user, sortBy, startIndex, amount);
        }
    }

    @Override
    public long findOrderAmount() {
        try(OrderDao orderDao = daoFactory.createOrderDao()){
            return orderDao.findOrdersAmount();
        }
    }

    @Override
    public long findUserOrdersAmount(User user) {
        try(OrderDao orderDao = daoFactory.createOrderDao()){
            return orderDao.findUserOrdersAmount(user);
        }
    }


}
