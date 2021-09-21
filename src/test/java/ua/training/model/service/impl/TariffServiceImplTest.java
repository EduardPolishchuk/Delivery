package ua.training.model.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.TariffDao;
import ua.training.model.entity.Tariff;

import java.util.Optional;

public class TariffServiceImplTest {

    private Tariff tariff;

    @Mock
    private DaoFactory daoFactoryMock;

    @Mock
    private TariffDao tariffDaoMock;

    @InjectMocks
    private TariffServiceImpl tariffServiceInstance;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createTariffDao()).thenReturn(tariffDaoMock);
        tariff = new Tariff();
        tariff.setId(2);
        tariff.setAdditional(20F);
        tariff.setUahPerMillimeterWidth(0.1F);
        tariff.setUahPerMillimeterHeight(0.1F);
        tariff.setUahPerMillimeterLength(0.1F);
        tariff.setUahPerKilogramWeight(0.1F);
        tariff.setUahPerKilometerDistance(0.1F);
    }

    @Test
    void shouldUpdateAndReturnTrue(){
        Mockito.when(tariffDaoMock.updateTariff(tariff)).thenReturn(true);
        Assertions.assertTrue(tariffServiceInstance.updateTariff(tariff));
    }

    @Test
    void shouldReturnOptionalTariff(){
        Mockito.when(tariffDaoMock.getTariff()).thenReturn(Optional.of(tariff));
        Optional<Tariff> optionalTariff = tariffServiceInstance.getTariff();
        Assertions.assertTrue(optionalTariff.isPresent());
        Assertions.assertEquals(optionalTariff.get(), tariff);
    }
}
