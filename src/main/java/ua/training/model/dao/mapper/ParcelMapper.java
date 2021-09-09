package ua.training.model.dao.mapper;

import ua.training.model.entity.Parcel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParcelMapper implements ObjectMapper<Parcel>{
    @Override
    public Parcel extractFromResultSet(ResultSet rs) throws SQLException {
        return Parcel.builder()
                .width(rs.getFloat("width"))
                .weight(rs.getFloat("weight"))
                .length(rs.getFloat("length"))
                .height(rs.getFloat("height"))
                .id(rs.getLong("parcel_id"))
                .type(rs.getString("type"))
                .build();
    }
}
