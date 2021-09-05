package ua.training.model.dao.mapper;

import ua.training.model.entity.Parcel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParcelMapper implements ObjectMapper<Parcel>{
    @Override
    public Parcel extractFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }
}
