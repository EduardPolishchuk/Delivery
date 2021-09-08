package ua.training.model.dao.impl;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.dao.property_reader.DBPropertyReader;
import ua.training.model.dao.OrderDao;
import ua.training.model.entity.Order;
import ua.training.model.entity.Parcel;

import java.sql.*;
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
    public boolean create(Order order) {
        long generatedID;
        Parcel parcel = order.getParcel();
        try (
                PreparedStatement ps = connection.prepareCall("INSERT INTO parcel (length, width, height, weight, type) VALUES (?,?,?,?,?)");
                PreparedStatement ps2 = connection.prepareStatement("INSERT INTO `order` ( request_date, parcel_id, user_sender, city_from, city_to, order_status)" +
                        " VALUES (?,?,?,?,?,?)")) {

            connection.setAutoCommit(false);
            int counter = 1;
            ps.setFloat(counter++, parcel.getLength());
            ps.setFloat(counter++, parcel.getWidth());
            ps.setFloat(counter++, parcel.getHeight());
            ps.setFloat(counter++, parcel.getWidth());
            ps.setString(counter, parcel.getType());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                generatedID = rs.getLong(1);
            }
            counter = 1;
            ps2.setDate(counter++, Date.valueOf(order.getRequestDate()));
            ps2.setLong(counter++, generatedID);
            ps2.setLong(counter++, order.getUserSender().getId());
            ps2.setLong(counter++, order.getCityFrom().getId());
            ps2.setLong(counter++, order.getCityTo().getId());
            ps2.setString(counter, order.getStatus().toString());
            ps2.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            close();
        }
        return false;
    }

    @Override
    public Optional<Order> findById(long id) {
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
