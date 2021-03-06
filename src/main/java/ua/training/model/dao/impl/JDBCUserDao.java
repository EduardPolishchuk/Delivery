package ua.training.model.dao.impl;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.dao.property_reader.DBPropertyReader;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entity.User;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class JDBCUserDao implements UserDao {

    private static final String BALANCE_REPLENISHMENT = "balanceReplenishment";
    private static final String GET_USER_BALANCE = "getUserBalance";
    private static final String USER_IS_VALID = "userIsValid";
    private static final String CREATE_USER = "createUser";

    private final Properties properties = DBPropertyReader.getProperties();
    private static final Logger logger = LogManager.getLogger(JDBCUserDao.class);
    private final Connection connection;


    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(User user) {
        try (PreparedStatement ps = connection.prepareStatement(properties.getProperty(CREATE_USER))){
            int i = 1;
            ps.setString(i++, user.getLogin());
            ps.setString(i++, user.getEmail());
            ps.setString(i++, user.getPassword());
            ps.setString(i++, user.getFirstName());
            ps.setString(i++, user.getLastName());
            ps.setInt(i, 2);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            return false;
        } finally {
            close();
        }
        return true;
    }

    @Override
    public Optional<User> findById(long id) {
        User user = null;
        UserMapper userMapper = new UserMapper();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM user LEFT JOIN role r on r.id = user.role where user.user_id =?")){

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = userMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM user LEFT JOIN role  on role.id = user.role where role_name = 'user'")) {
            ResultSet rs = ps.executeQuery();
            UserMapper userMapper = new UserMapper();
            while (rs.next()) {
                users.add(userMapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return users;
    }


    @Override
    public boolean update(User user) {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE user SET email=?,password=?,first_name=?,last_name=? WHERE user_id =?")) {
            int i = 1;
            ps.setString(i++, user.getEmail());
            ps.setString(i++, user.getPassword());
            ps.setString(i++, user.getFirstName());
            ps.setString(i++, user.getLastName());
            ps.setLong(i, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            return false;
        } finally {
            close();
        }
        return true;
    }

    @Override
    public Optional<User> findIfValid(String login, String password) {
        Optional<User> result = Optional.empty();
        UserMapper userMapper = new UserMapper();
        try (PreparedStatement ps = connection.prepareStatement(properties.getProperty(USER_IS_VALID))) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                result = Optional.of(userMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            close();
        }
        return result;
    }


    public BigDecimal getUserBalance(User user) {
        BigDecimal balance = BigDecimal.valueOf(0);
        try (PreparedStatement ps = connection.prepareStatement(properties.getProperty(GET_USER_BALANCE))){

            ps.setLong(1, user.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                balance = rs.getBigDecimal(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            close();
        }
        return balance;
    }

    @Override
    public BigDecimal balanceReplenishment(BigDecimal amount, User user) {
        try (PreparedStatement ps = connection.prepareStatement(properties.getProperty(BALANCE_REPLENISHMENT))) {
            ps.setBigDecimal(1, amount);
            ps.setLong(2, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return getUserBalance(user);
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
