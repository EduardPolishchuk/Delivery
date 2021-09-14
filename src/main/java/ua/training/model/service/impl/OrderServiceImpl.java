package ua.training.model.service.impl;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.OrderDao;
import ua.training.model.entity.Order;
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
}