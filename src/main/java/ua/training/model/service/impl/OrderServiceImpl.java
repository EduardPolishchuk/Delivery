package ua.training.model.service.impl;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.OrderDao;
import ua.training.model.dao.TariffDao;
import ua.training.model.entity.*;
import ua.training.model.service.OrderService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public Optional<Order> findById(long id) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findById(id);
        }
    }

    @Override
    public boolean create(Order order) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.create(order);
        }
    }

    @Override
    public boolean update(Order order) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.update(order);
        }
    }

    @Override
    public List<Order> findAll() {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findAll();
        }
    }



    @Override
    public List<Order> findUserOrders(User user) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findUserOrders(user);
        }
    }

     @Override
    public BigDecimal calculateOrderPrice(Order order) {
        try (TariffDao tariffDao = daoFactory.createTariffDao()) {
            Tariff tariff = tariffDao.getTariff().get();
            Parcel parcel = order.getParcel();
            return BigDecimal.valueOf(tariff.getUahPerKilogramWeight() * parcel.getWeight()
                    + tariff.getUahPerMillimeterHeight() * parcel.getHeight()
                    + tariff.getUahPerMillimeterLength() * parcel.getLength()
                    + tariff.getUahPerMillimeterWidth() * parcel.getWidth()
                    + tariff.getUahPerKilogramWeight() * distFrom(order.getCityFrom(), order.getCityTo())
                    + tariff.getAdditional()).setScale(1,BigDecimal.ROUND_HALF_UP);
        }
    }

       private float distFrom(City cityFrom, City cityTo) {
        float lat1 = cityFrom.getLatitude();
        float lat2 = cityTo.getLatitude();
        float lng1 = cityFrom.getLongitude();
        float lng2 = cityTo.getLongitude();

        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (float) (earthRadius * c) / 1000;
    }
}
