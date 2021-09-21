package ua.training.model.service.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.training.model.dao.impl.JDBCCityDao;
import ua.training.model.dao.impl.JDBCDaoFactory;
import ua.training.model.entity.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CityServiceImplTest {

    private City city;
    private List<City> list;

    @Mock
    private JDBCDaoFactory daoFactoryMock;
    @Mock
    private JDBCCityDao cityDaoMock;
    @InjectMocks
    private CityServiceImpl cityServiceInstance;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createCityDao()).thenReturn(cityDaoMock);
        city = City.builder()
                .id(2)
                .nameUk("CityUk")
                .name("City")
                .latitude(40f)
                .longitude(45f)
                .build();
        list = new ArrayList<>();
        list.add(city);
    }


    @Test
    void shouldReturnCityByID() {
        Mockito.when(cityDaoMock.findById(city.getId())).thenReturn(Optional.of(city));
        Optional<City> cityOptional = cityServiceInstance.findById(city.getId());
        Assertions.assertTrue(cityOptional.isPresent());
        Assertions.assertEquals(city, cityOptional.get());
    }

    @Test
    void shouldReturnConvertToDecimalDegrees() {
        float result = cityServiceInstance.convertToDecimalDegrees(40F, 40F, 40F);
        float expected = 40.67778F;
        Assertions.assertTrue(Math.abs(result - expected) < 0.1);
    }

    @Test
    void shouldCreateCityAndReturnTrue() {
        Mockito.when(cityDaoMock.create(city)).thenReturn(true);
        Assertions.assertTrue(cityServiceInstance.create(city));
    }

    @Test
    void shouldUpdateCityAndReturnTrue() {
        Mockito.when(cityDaoMock.update(city)).thenReturn(true);
        Assertions.assertTrue(cityServiceInstance.update(city));
    }

    @Test
    void shouldReturnCityList() {
        Mockito.when(cityDaoMock.findAll()).thenReturn(list);
        Assertions.assertTrue(cityServiceInstance.findAll().contains(city));
    }
}
