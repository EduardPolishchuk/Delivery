package ua.training.model.dao.mapper;

import ua.training.model.entity.Receipt;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceiptMapper implements ObjectMapper<Receipt>{
    @Override
    public Receipt extractFromResultSet(ResultSet rs) throws SQLException {
        return Receipt.builder().build();
    }
}
