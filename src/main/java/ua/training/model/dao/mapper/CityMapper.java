package ua.training.model.dao.mapper;

import ua.training.model.entity.City;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityMapper implements ObjectMapper<City>{
    @Override
    public City extractFromResultSet(ResultSet rs) throws SQLException {
        return City.builder()
                .name(rs.getString("name"))
                .nameUk(rs.getString("name_uk"))
                .id(rs.getLong("id"))
                .longitude(rs.getFloat("longitude"))
                .latitude(rs.getFloat("latitude"))
                .build();
    }
}
