package ua.training.model.dao.impl;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.dao.mapper.CityMapper;
import ua.training.model.dao.mapper.OrderMapper;
import ua.training.model.dao.mapper.ParcelMapper;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.dao.property_reader.DBPropertyReader;
import ua.training.model.dao.OrderDao;
import ua.training.model.entity.Order;
import ua.training.model.entity.Parcel;
import ua.training.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
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
            ps2.setString(counter, Order.OrderStatus.WAITING_FOR_CONFIRM.toString());
            ps2.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            rollback(connection);
        } finally {
            close();
        }
        return false;
    }

    @Override
    public Optional<Order> findById(long id) {
        try (
                PreparedStatement psOrder = connection.prepareStatement("SELECT *\n" +
                        "FROM `order`\n" +
                        "         LEFT JOIN parcel  on `order`.parcel_id = parcel.parcel_id\n" +
                        "         LEFT JOIN user  on user_id = `order`.user_sender\n" +
                        "         LEFT JOIN role user_role on role = user_role.id\n" +
                        "         left join city city_from on city_from.id = `order`.city_from\n" +
                        "         left join city city_to on city_to.id = `order`.city_to WHERE `order`.id =?")) {

            psOrder.setLong(1, id);
            return Optional.ofNullable(getOrderListByPreparedStatement(psOrder).get(0));
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Order> findAll() {
        try (PreparedStatement psOrder = connection.prepareStatement("SELECT *\n" +
                "FROM `order`\n" +
                "         LEFT JOIN parcel  on `order`.parcel_id = parcel.parcel_id\n" +
                "         LEFT JOIN user  on user_id = `order`.user_sender\n" +
                "         LEFT JOIN role user_role on role = user_role.id\n" +
                "         left join city city_from on city_from.id = `order`.city_from\n" +
                "         left join city city_to on city_to.id = `order`.city_to ")) {

            return getOrderListByPreparedStatement(psOrder);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            return new ArrayList<>();
        }
    }


    @Override
    public List<Order> findSortedUserOrdersFromIndex(User user, String sortBy, long startIndex, int limit) {
        int orderBy = getOrderBy(sortBy);
        try (PreparedStatement psOrder = connection.prepareStatement("SELECT *\n" +
                "FROM `order`\n" +
                "         LEFT JOIN parcel  on `order`.parcel_id = parcel.parcel_id\n" +
                "         LEFT JOIN user  on user_id = `order`.user_sender\n" +
                "         LEFT JOIN role user_role on role = user_role.id\n" +
                "         left join city city_from on city_from.id = `order`.city_from\n" +
                "         left join city city_to on city_to.id = `order`.city_to where user_sender =?" +
                " ORDER BY ?   LIMIT ? OFFSET ?"
        )) {
            int counter = 1;
            psOrder.setLong(counter++, user.getId());
            psOrder.setInt(counter++, orderBy);
            psOrder.setLong(counter++, limit);
            psOrder.setLong(counter, startIndex);
            return getOrderListByPreparedStatement(psOrder);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Order> findSortedOrdersFromIndex(String sortBy, long startIndex, int limit) {
        int orderBy;
        orderBy = getOrderBy(sortBy);
        try (PreparedStatement psOrder = connection.prepareStatement("SELECT *\n" +
                "FROM `order`\n" +
                "         LEFT JOIN parcel  on `order`.parcel_id = parcel.parcel_id\n" +
                "         LEFT JOIN user  on user_id = `order`.user_sender\n" +
                "         LEFT JOIN role user_role on role = user_role.id\n" +
                "         left join city city_from on city_from.id = `order`.city_from\n" +
                "         left join city city_to on city_to.id = `order`.city_to where order_status =?" +
                " ORDER BY ?   LIMIT ? OFFSET ?"
        )) {
            int counter = 1;
            psOrder.setString(counter++, Order.OrderStatus.WAITING_FOR_CONFIRM.toString());
            psOrder.setInt(counter++, orderBy);
            psOrder.setLong(counter++, limit);
            psOrder.setLong(counter, startIndex);
            return getOrderListByPreparedStatement(psOrder);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            return new ArrayList<>();
        }
    }

    private int getOrderBy(String sortBy) {
        int orderBy;
        if (sortBy.contains("Desc")) {
            sortBy = sortBy.replace("Desc", "");
        }
        switch (sortBy) {
            case "requestDate":
                orderBy = 3;
                break;
            case "cityFrom":
                orderBy = 5;
                break;
            case "cityTo":
                orderBy = 6;
                break;
            case "status":
                orderBy = 8;
                break;
            case "receiveDate":
                orderBy = 2;
                break;
            default:
                orderBy = 1;
                break;
        }
        return orderBy;
    }

    @Override
    public List<Order> findUserOrders(User user) {
        try (PreparedStatement psOrder = connection.prepareStatement("SELECT *\n" +
                "FROM `order`\n" +
                "         LEFT JOIN parcel  on `order`.parcel_id = parcel.parcel_id\n" +
                "         LEFT JOIN user  on user_id = `order`.user_sender\n" +
                "         LEFT JOIN role user_role on role = user_role.id\n" +
                "         left join city city_from on city_from.id = `order`.city_from\n" +
                "         left join city city_to on city_to.id = `order`.city_to where user_sender =?")) {

            psOrder.setLong(1, user.getId());
            return getOrderListByPreparedStatement(psOrder);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public long findOrdersForConfirmAmount() {
        long rows = 0;
        try (PreparedStatement ps = connection.prepareStatement("SELECT COUNT(1) from `order` where order_status =?")) {
           ps.setString(1, Order.OrderStatus.WAITING_FOR_CONFIRM.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rows = rs.getLong(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return rows;
    }

    @Override
    public long findUserOrdersAmount(User user) {
        long rows = 0;
        try (PreparedStatement ps = connection.prepareStatement("SELECT COUNT(1) from `order` where user_sender = ? ")) {
            ps.setLong(1, user.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rows = rs.getLong(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return rows;
    }


    @Override
    public boolean update(Order entity) {
        return false;
    }

    private List<Order> getOrderListByPreparedStatement(PreparedStatement ps) {
        List<Order> list = new ArrayList<>();
        OrderMapper orderMapper = new OrderMapper();
        ParcelMapper parcelMapper = new ParcelMapper();
        CityMapper cityMapper = new CityMapper();
        UserMapper userMapper = new UserMapper();
        Order order;
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                order = orderMapper.extractFromResultSet(rs);
                order.setParcel(parcelMapper.extractFromResultSet(rs));
                order.setCityFrom(cityMapper.extractFromResultSet(rs, "city_from."));
                order.setCityTo(cityMapper.extractFromResultSet(rs, "city_to."));
                order.setUserSender(userMapper.extractFromResultSet(rs));
                list.add(order);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
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
