package ua.training.model.service;

import ua.training.model.entity.Tariff;

import java.util.Optional;

public interface TariffService {

    Optional<Tariff> getTariff();
    boolean updateTariff(Tariff tariff);
}
