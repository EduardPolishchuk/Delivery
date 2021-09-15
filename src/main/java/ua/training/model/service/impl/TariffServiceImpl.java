package ua.training.model.service.impl;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.TariffDao;
import ua.training.model.entity.Tariff;
import ua.training.model.service.TariffService;

import java.util.Optional;

public class TariffServiceImpl implements TariffService {
    private DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public Optional<Tariff> getTariff() {
        try(TariffDao tariffDao = daoFactory.createTariffDao()){
            return tariffDao.getTariff();
        }
    }

    @Override
    public boolean updateTariff(Tariff tariff) {
        try(TariffDao tariffDao = daoFactory.createTariffDao()){
            return tariffDao.updateTariff(tariff);
        }
    }
}
