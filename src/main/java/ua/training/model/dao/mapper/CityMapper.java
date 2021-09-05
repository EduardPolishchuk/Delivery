package ua.training.model.dao.mapper;

import ua.training.model.entity.City;
import ua.training.model.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityMapper implements ObjectMapper<City>{
    @Override
    public City extractFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }
}
