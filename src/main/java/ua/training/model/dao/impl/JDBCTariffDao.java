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
            if(rs.next()){
                return Optional.ofNullable(tariffMapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean updateTariff(Tariff tariff) {
        return false;
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
