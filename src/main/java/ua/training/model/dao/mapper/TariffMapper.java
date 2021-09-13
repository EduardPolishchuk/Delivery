package ua.training.model.dao.mapper;

import ua.training.model.entity.Tariff;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TariffMapper implements ObjectMapper<Tariff> {

    @Override
    public Tariff extractFromResultSet(ResultSet rs) throws SQLException {
        Tariff tariff = new Tariff();
        tariff.setUahPerMillimeterLength(rs.getFloat("uah_per_mm_length"));
        tariff.setUahPerMillimeterHeight(rs.getFloat("uah_per_mm_height"));
        tariff.setUahPerMillimeterWidth(rs.getFloat("uah_per_mm_width"));
        tariff.setUahPerKilogramWeight(rs.getFloat("uah_per_kg_weight"));
        tariff.setUahPerKilometerDistance(rs.getFloat("uah_per_km_distance"));

        return tariff;
    }
}
