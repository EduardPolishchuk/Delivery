package ua.training.model.dao.impl;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.dao.ReceiptDao;
import ua.training.model.dao.property_reader.DBPropertyReader;
import ua.training.model.entity.Receipt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class JDBCReceiptDao implements ReceiptDao {

    private final Properties properties = DBPropertyReader.getProperties();
    private static final Logger logger = LogManager.getLogger(JDBCUserDao.class);
    private final Connection connection;

    public JDBCReceiptDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Receipt> findById(long id) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * from receipt left join `order`  on `order`.id = receipt.order_id " +
                "WHERE receipt.id = ?")) {
            ps.setLong(1, id);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean create(Receipt entity) {
        return false;
    }

    @Override
    public boolean update(Receipt entity) {
        return false;
    }

    @Override
    public List<Receipt> findAll() {
        return null;
    }

    @Override
    public void close() {

    }
}
