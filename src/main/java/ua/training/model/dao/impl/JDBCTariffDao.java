package ua.training.model.dao.impl;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.dao.TariffDao;
import ua.training.model.dao.mapper.TariffMapper;
import ua.training.model.dao.property_reader.DBPropertyReader;
import ua.training.model.entity.Tariff;

import java.sql.*;
import java.util.Optional;
import java.util.Properties;

public class JDBCTariffDao implements TariffDao {

    private final Properties properties = DBPropertyReader.getProperties();
    private static final Logger logger = LogManager.getLogger(JDBCTariffDao.class);
    private final Connection connection;


    public JDBCTariffDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Optional<Tariff> getTariff() {
        TariffMapper tariffMapper = new TariffMapper();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM tariff")) {
            if (rs.next()) {
                return Optional.ofNullable(tariffMapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean updateTariff(Tariff tariff) {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE tariff set uah_per_kg_weight=?" +
                ", uah_per_km_distance = ?" +
                ", uah_per_mm_height = ?" +
                ", uah_per_mm_length = ?" +
                ", uah_per_mm_width = ?" +
                ", additional= ? where tariff_id = ?")) {
            int counter = 1;
            ps.setFloat(counter++, tariff.getUahPerKilogramWeight());
            ps.setFloat(counter++, tariff.getUahPerKilometerDistance());
            ps.setFloat(counter++, tariff.getUahPerMillimeterHeight());
            ps.setFloat(counter++, tariff.getUahPerMillimeterLength());
            ps.setFloat(counter++, tariff.getUahPerMillimeterWidth());
            ps.setFloat(counter++, tariff.getAdditional());
            ps.setLong(counter, tariff.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            return false;
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }
}
