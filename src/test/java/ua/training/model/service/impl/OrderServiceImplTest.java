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
import ua.training.model.dao.TariffDao;
import ua.training.model.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderServiceImplTest {

    private Order order;
    private Tariff tariff;
    private User user;
    private Parcel parcel;
    private City cityFrom;
    private City cityTo;
    private List<Order> list;

    @Mock
    private DaoFactory daoFactoryMock;
    @Mock
    private OrderDao orderDaoMock;
    @Mock
    private TariffDao tariffDaoMock;
    @InjectMocks
    private OrderServiceImpl orderServiceInstance;


    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createOrderDao()).thenReturn(orderDaoMock);
        parcel = Parcel.builder()
                .width(30)
                .weight(10)
                .length(30)
                .height(30)
                .build();
        user = User.builder()
                .id(2)
                .lastName("Name")
                .build();
        cityTo = City.builder()
                .longitude(30)
                .latitude(30)
                .name("CityTo")
                .nameUk("CityToUk")
                .build();
        cityFrom = City.builder()
                .longitude(40)
                .latitude(40)
                .name("cityFrom")
                .nameUk("cityFromUk")
                .build();
        order = Order.builder()
                .id(2)
                .cityTo(cityTo)
                .cityFrom(cityFrom)
                .parcel(parcel)
                .userSender(user)
                .receivingDate(LocalDate.now())
                .requestDate(LocalDate.now())
                .status(Order.OrderStatus.PARCEL_DELIVERY)
                .build();
        tariff = new Tariff();
        tariff.setUahPerKilometerDistance(0.1F);
        tariff.setUahPerMillimeterWidth(0.1F);
        tariff.setUahPerMillimeterLength(0.1F);
        tariff.setUahPerMillimeterHeight(0.1F);
        tariff.setUahPerKilogramWeight(0.1F);
        tariff.setAdditional(10F);


        list = new ArrayList<>();
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
    void shouldReturnOrderList() {
        Mockito.when(orderDaoMock.findAll()).thenReturn(list);
        Assertions.assertTrue(orderServiceInstance.findAll().contains(order));
    }

    @Test
    void shouldReturnUserOrderList() {
        Mockito.when(orderDaoMock.findUserOrders(user)).thenReturn(list);
        Assertions.assertTrue(orderServiceInstance.findUserOrders(user)
                .contains(order));
    }

    @Test
    void shouldCreateUserAndReturnTrue() {
        Mockito.when(orderDaoMock.create(order)).thenReturn(true);
        Assertions.assertTrue(orderServiceInstance.create(order));
    }

    @Test
    void shouldUpdateUserAndReturnTrue() {
        Mockito.when(orderDaoMock.update(order)).thenReturn(true);
        Assertions.assertTrue(orderServiceInstance.update(order));
    }

    @Test
    void shouldReturnCalculatedOrderPrice() {
        Mockito.when(daoFactoryMock.createTariffDao()).thenReturn(tariffDaoMock);
        Mockito.when(tariffDaoMock.getTariff()).thenReturn(Optional.of(tariff));

        Assertions.assertTrue(
                Math.abs(orderServiceInstance.calculateOrderPrice(order).floatValue() - 163.5) < 0.01);
    }
}
