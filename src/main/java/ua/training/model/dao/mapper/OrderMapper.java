package ua.training.model.dao.mapper;

import ua.training.model.entity.City;
import ua.training.model.entity.Order;
import ua.training.model.entity.Parcel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class OrderMapper implements ObjectMapper<Order> {
    @Override
    public Order extractFromResultSet(ResultSet rs) throws SQLException {
        return Order.builder()
                .id(rs.getLong("id"))
                .requestDate(toLocalDate(rs.getDate("request_date")))
                .status(Order.OrderStatus.valueOf(rs.getString("order_status")))
                .confirmed(rs.getBoolean("confirmed"))
                .build();
    }

    private static LocalDate toLocalDate(Date date) {
        return LocalDate.from(Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault()));
    }
}
