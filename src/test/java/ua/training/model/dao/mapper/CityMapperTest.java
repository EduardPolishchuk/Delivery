package ua.training.model.dao.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.training.model.entity.City;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityMapperTest {
    private CityMapper cityMapper ;
    private City city;

    @Mock
    private ResultSet resultSet;

    @BeforeEach
    public void init() {
        cityMapper = new CityMapper();
        city = City.builder()
                .id(2)
                .longitude(49f)
                .latitude(50f)
                .name("City")
                .nameUk("CityUk")
                .build();
    }


    @Test
    public void shouldReturnCorrectCity() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(resultSet.getLong("id")).thenReturn(city.getId());
        Mockito.when(resultSet.getString("name")).thenReturn(city.getName());
        Mockito.when(resultSet.getString("name_uk")).thenReturn(city.getNameUk());
        Mockito.when(resultSet.getFloat("latitude")).thenReturn(city.getLatitude());
        Mockito.when(resultSet.getFloat("longitude")).thenReturn(city.getLongitude());

        City cityFromMapper = cityMapper.extractFromResultSet(resultSet);
        Assertions.assertEquals(city.getId(), cityFromMapper.getId());
        Assertions.assertEquals(city.getName(), cityFromMapper.getName());
        Assertions.assertEquals(city.getNameUk(), cityFromMapper.getNameUk());
        Assertions.assertEquals(city.getLatitude(), cityFromMapper.getLatitude());
        Assertions.assertEquals(city.getLongitude(), cityFromMapper.getLongitude());
    }

    @Test
    public void shouldReturnCorrectCityWithPrefix() throws SQLException {
        String prefix = "city_to.";
        MockitoAnnotations.initMocks(this);
        Mockito.when(resultSet.getLong(prefix + "id")).thenReturn(city.getId());
        Mockito.when(resultSet.getString(prefix +"name")).thenReturn(city.getName());
        Mockito.when(resultSet.getString(prefix +"name_uk")).thenReturn(city.getNameUk());
        Mockito.when(resultSet.getFloat(prefix +"latitude")).thenReturn(city.getLatitude());
        Mockito.when(resultSet.getFloat(prefix +"longitude")).thenReturn(city.getLongitude());

        City cityFromMapper = cityMapper.extractFromResultSet(resultSet, prefix);
        Assertions.assertEquals(city.getId(), cityFromMapper.getId());
        Assertions.assertEquals(city.getName(), cityFromMapper.getName());
        Assertions.assertEquals(city.getNameUk(), cityFromMapper.getNameUk());
        Assertions.assertEquals(city.getLatitude(), cityFromMapper.getLatitude());
        Assertions.assertEquals(city.getLongitude(), cityFromMapper.getLongitude());
    }
}
