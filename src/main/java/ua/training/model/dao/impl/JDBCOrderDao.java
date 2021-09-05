package ua.training.model.dao.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.dao.DBPropertyReader;
import ua.training.model.dao.OrderDao;
import ua.training.model.entity.Order;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class JDBCOrderDao implements OrderDao {
    private final Properties properties = DBPropertyReader.getProperties();
    private static final Logger logger = LogManager.getLogger(JDBCUserDao.class);
    private final Connection connection;


    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Order entity) {
        return false;
    }

    @Override
    public Optional<Order> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public boolean update(Order entity) {
        return false;
    }

    @Override
    public void close() {

    }
}
