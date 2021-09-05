package ua.training.model.dao.mapper;

import ua.training.model.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements ObjectMapper<Order>{
    @Override
    public Order extractFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }
}
