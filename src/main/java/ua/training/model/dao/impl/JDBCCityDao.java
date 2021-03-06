package ua.training.model.dao.impl;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.dao.CityDao;
import ua.training.model.dao.property_reader.DBPropertyReader;
import ua.training.model.dao.mapper.CityMapper;
import ua.training.model.entity.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class JDBCCityDao implements CityDao {


    private final Properties properties = DBPropertyReader.getProperties();
    private static final Logger LOGGER = LogManager.getLogger(JDBCCityDao.class);
    private final Connection connection;


    public JDBCCityDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(City city) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO city (name, name_uk, latitude, longitude) VALUES (?,?,?,?)")) {
            int k = 1;
            ps.setString(k++, city.getName());
            ps.setString(k++, city.getNameUk());
            ps.setFloat(k++, city.getLatitude());
            ps.setFloat(k, city.getLongitude());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Optional<City> findById(long id) {
        City city = null;
        CityMapper cityMapper = new CityMapper();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM city WHERE id = ?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                city = rs.next() ? cityMapper.extractFromResultSet(rs) : null;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
        return Optional.ofNullable(city);
    }

    @Override
    public List<City> findAll() {
        List<City> list = new ArrayList<>();
        CityMapper cityMapper = new CityMapper();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM city");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(cityMapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
        return list;
    }

    @Override
    public boolean update(City city) {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE city SET name = ?, name_uk = ?, latitude = ?, longitude = ? WHERE id = ?")) {
            int k = 1;
            ps.setString(k++, city.getName());
            ps.setString(k++, city.getNameUk());
            ps.setFloat(k++, city.getLatitude());
            ps.setFloat(k++, city.getLongitude());
            ps.setLong(k, city.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            return false;
        }
        return true;
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
