package ua.training.model.dao.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.training.model.entity.City;
import ua.training.model.entity.Order;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderMapperTest {
    private OrderMapper orderMapper;
    private Order order;

    @Mock
    private ResultSet resultSet;

    @BeforeEach
    public void init() {
        orderMapper = new OrderMapper();
        order = Order.builder()
                .id(3L)
                .requestDate(LocalDate.now())
                .receivingDate(LocalDate.now().plusDays(1))
                .status(Order.OrderStatus.PARCEL_DELIVERY)
                .build();
    }


    @Test
    public void shouldReturnCorrectCity() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(resultSet.getLong("id")).thenReturn(order.getId());
        Mockito.when(resultSet.getDate("request_date")).thenReturn(Date.valueOf(order.getRequestDate()));
        Mockito.when(resultSet.getDate("receiving_date")).thenReturn(Date.valueOf(order.getReceivingDate()));
        Mockito.when(resultSet.getString("order_status")).thenReturn(order.getStatus().toString());

        Order orderFromMapper = orderMapper.extractFromResultSet(resultSet);
        Assertions.assertEquals(order, orderFromMapper);
        Assertions.assertEquals(order.getId(), orderFromMapper.getId());
        Assertions.assertEquals(order.getReceivingDate(), orderFromMapper.getReceivingDate());
        Assertions.assertEquals(order.getRequestDate(), orderFromMapper.getRequestDate());
        Assertions.assertEquals(order.getStatus(), orderFromMapper.getStatus());
    }

}
