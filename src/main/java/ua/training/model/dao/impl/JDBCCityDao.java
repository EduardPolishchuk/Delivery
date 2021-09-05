package ua.training.model.dao.impl;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.dao.CityDao;
import ua.training.model.dao.DBPropertyReader;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.mapper.CityMapper;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entity.City;
import ua.training.model.entity.User;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class JDBCCityDao implements CityDao {


    private final Properties properties = DBPropertyReader.getProperties();
    private static final Logger logger = LogManager.getLogger(JDBCCityDao.class);
    private final Connection connection;


    public JDBCCityDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(City entity) {
        return false;
    }

    @Override
    public Optional<City> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<City> findAll() {
        List<City> list = new ArrayList<>();
        CityMapper cityMapper = new CityMapper();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM city");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()){
                list.add(cityMapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return list;
    }

    @Override
    public boolean update(City entity) {
        return false;
    }

    @Override
    public void close() {

    }
}
