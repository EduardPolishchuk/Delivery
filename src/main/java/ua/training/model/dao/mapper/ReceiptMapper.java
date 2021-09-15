package ua.training.model.dao.mapper;

import ua.training.model.entity.Order;
import ua.training.model.entity.Receipt;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceiptMapper implements ObjectMapper<Receipt>{
    @Override
    public Receipt extractFromResultSet(ResultSet rs) throws SQLException {
        return Receipt.builder()
                .id(rs.getLong("id"))
                .order(Order.builder().id(rs.getLong("order_id")).build())
                .paid(rs.getBoolean("paid"))
                .price(rs.getBigDecimal("price"))
                .build();
    }
}
