package ua.training.model.service.impl;

import ua.training.model.dao.CityDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.entity.City;
import ua.training.model.service.CityService;

import java.util.List;
import java.util.Optional;

public class CityServiceImpl implements CityService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public List<City> findAll() {
        try(CityDao cityDao = daoFactory.createCityDao()){
            return cityDao.findAll();
        }
    }

    @Override
    public Optional<City> findById(long id) {
        try(CityDao cityDao = daoFactory.createCityDao()){
            return cityDao.findById(id);
        }
    }

    @Override
    public boolean create(City city) {
        try(CityDao cityDao = daoFactory.createCityDao()){
            return cityDao.create(city);
        }
    }

    @Override
    public boolean update(City city) {
        try(CityDao cityDao = daoFactory.createCityDao()){
            return cityDao.update(city);
        }
    }

    public void doSmthng(){

    }
}
