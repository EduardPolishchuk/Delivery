package ua.training.model.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.OrderDao;
import ua.training.model.entity.Order;
import ua.training.model.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderServiceImplTest {

    private Order order;
    private User user;
    private List<Order> list;

    @Mock
    private DaoFactory daoFactoryMock;
    @Mock
    private OrderDao orderDaoMock;
    @InjectMocks
    private OrderServiceImpl orderServiceInstance;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createOrderDao()).thenReturn(orderDaoMock);
        order = Order.builder()
                .id(2)
                .receivingDate(LocalDate.now())
                .requestDate(LocalDate.now())
                .status(Order.OrderStatus.PARCEL_DELIVERY)
                .build();
        list = new ArrayList<>();
        user = User.builder()
                .id(2)
                .lastName("Name")
                .build();

        list.add(order);
    }

    @Test
    void shouldReturnOrderByID() {
        Mockito.when(orderDaoMock.findById(order.getId()))
                .thenReturn(Optional.of(order));
        Optional<Order> optionalOrder = orderServiceInstance.findById(order.getId());
        Assertions.assertTrue(optionalOrder.isPresent());
        Assertions.assertEquals(order, optionalOrder.get());

    }


    @Test
    void shouldReturnOrderList(){
        Mockito.when(orderDaoMock.findAll()).thenReturn(list);
        Assertions.assertTrue(orderServiceInstance.findAll().contains(order));
    }

    @Test
    void shouldReturnUserOrderList(){
        Mockito.when(orderDaoMock.findUserOrders(user)).thenReturn(list);
        Assertions.assertTrue(orderServiceInstance.findUserOrders(user)
                .contains(order));
    }
}
