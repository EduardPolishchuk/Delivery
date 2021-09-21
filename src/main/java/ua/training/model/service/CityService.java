package ua.training.model.service;

import ua.training.model.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityService {

    List<City> findAll();

    Optional<City> findById(long id);

    boolean create(City city);

    boolean update(City city);

    float convertToDecimalDegrees(float deg, float min, float sec);
}
