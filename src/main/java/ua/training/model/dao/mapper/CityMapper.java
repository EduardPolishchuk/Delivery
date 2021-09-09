package ua.training.model.dao.mapper;

import ua.training.model.entity.City;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityMapper implements ObjectMapper<City>{
    @Override
    public City extractFromResultSet(ResultSet rs) throws SQLException {
        return extractFromResultSet(rs, "");
    }

    public City extractFromResultSet(ResultSet rs, String prefix) throws SQLException {
        return City.builder()
                .name(rs.getString(prefix + "name"))
                .nameUk(rs.getString(prefix + "name_uk"))
                .id(rs.getLong(prefix + "id"))
                .longitude(rs.getFloat(prefix + "longitude"))
                .latitude(rs.getFloat(prefix + "latitude"))
                .build();
    }
}
