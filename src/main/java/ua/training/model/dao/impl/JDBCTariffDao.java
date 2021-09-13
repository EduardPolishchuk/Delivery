package ua.training.model.dao.impl;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.dao.TariffDao;
import ua.training.model.dao.property_reader.DBPropertyReader;
import ua.training.model.entity.Tariff;

import java.sql.*;
import java.util.Properties;

public class JDBCTariffDao implements TariffDao {

    private final Properties properties = DBPropertyReader.getProperties();
    private static final Logger logger = LogManager.getLogger(JDBCUserDao.class);
    private final Connection connection;


    public JDBCTariffDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Tariff getTariff() {
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM tariff")) {

        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateTariff() {
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
