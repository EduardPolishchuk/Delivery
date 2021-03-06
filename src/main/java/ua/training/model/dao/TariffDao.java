package ua.training.model.dao;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.entity.Tariff;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface TariffDao extends AutoCloseable {
    Logger logger = LogManager.getLogger(GenericDao.class);

    Optional<Tariff> getTariff();
    boolean updateTariff(Tariff tariff);
    void close();

    default  void rollback(Connection connection){
        if (connection != null){
            try {
                connection.rollback();
            }catch (SQLException e){
                logger.log(Level.ERROR, e.getMessage());
            }
        }
    }
}
