package ua.training.model.service.impl;

import ua.training.model.dao.CityDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.entity.City;
import ua.training.model.service.CityService;

import java.util.List;

public class CityServiceImpl implements CityService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public List<City> findAll() {
        List<City> list ;
        try(CityDao cityDao = daoFactory.createCityDao()){
            list = cityDao.findAll();
        }
        return list;
    }
}
