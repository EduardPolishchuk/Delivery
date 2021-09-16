package ua.training.model.dao.impl;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.dao.ReceiptDao;
import ua.training.model.dao.mapper.ReceiptMapper;
import ua.training.model.dao.property_reader.DBPropertyReader;
import ua.training.model.entity.Order;
import ua.training.model.entity.Receipt;
import ua.training.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        ReceiptMapper receiptMapper = new ReceiptMapper();
        Receipt receipt = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * from receipt " +
                "WHERE receipt.id = ?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    receipt = receiptMapper.extractFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return Optional.ofNullable(receipt);
    }

    @Override
    public boolean create(Receipt receipt) {
        try (PreparedStatement insertReceipt = connection.prepareStatement("INSERT INTO receipt (price, paid, order_id)" +
                " VALUES (?,?,?)");
             PreparedStatement updateOrder = connection.prepareStatement("UPDATE `order` set order_status = ? where id = ?")) {

            connection.setAutoCommit(false);
            int counter = 1;
            insertReceipt.setBigDecimal(counter++, receipt.getPrice());
            insertReceipt.setBoolean(counter++, receipt.isPaid());
            insertReceipt.setLong(counter, receipt.getOrder().getId());
            counter = 1;
            updateOrder.setString(counter++, Order.OrderStatus.WAITING_FOR_PAYMENT.toString());
            updateOrder.setLong(counter, receipt.getOrder().getId());
            insertReceipt.executeUpdate();
            updateOrder.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            rollback(connection);
            return false;
        }
    }

    @Override
    public boolean update(Receipt entity) {
        return false;
    }

    @Override
    public List<Receipt> findAll() {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM receipt ")) {
            return getReceiptsByPreparedStatement(ps);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Receipt> findUserReceipts(User user, boolean paid) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM receipt left join `order` o on o.id = receipt.order_id where o.user_sender = ? and paid = ?")) {
            ps.setLong(1, user.getId());
            ps.setBoolean(2, paid);
            return getReceiptsByPreparedStatement(ps);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean userPaysReceipt(User user, Receipt receipt) {

        try (PreparedStatement paymentStatement = connection.prepareStatement("UPDATE user, `order`, receipt set paid = 1, balance = balance - receipt.price" +
                ", order_status = ? where user_id = `order`.user_sender and `order`.id = receipt.order_id and user_id=? and receipt.id =?")) {
            connection.setAutoCommit(false);
            int counter = 1;
            paymentStatement.setString(counter++, Order.OrderStatus.PARCEL_DELIVERY.toString());
            paymentStatement.setLong(counter++, user.getId());
            paymentStatement.setLong(counter, receipt.getId());
            paymentStatement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            rollback(connection);
            return false;
        }
    }

    private List<Receipt> getReceiptsByPreparedStatement(PreparedStatement ps) throws SQLException {
        List<Receipt> list = new ArrayList<>();
        ReceiptMapper receiptMapper = new ReceiptMapper();
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(receiptMapper.extractFromResultSet(rs));
            }
        }
        return list;
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
