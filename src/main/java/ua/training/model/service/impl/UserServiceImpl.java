package ua.training.model.service.impl;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public Optional<User> findIfValid(String login, String password) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findIfValid(login, password);
        }
    }

    @Override
    public Optional<User> findById(long id) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findById(id);
        }
    }

    @Override
    public boolean create(User user) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.create(user);
        }
    }

    @Override
    public boolean update(User user) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.update(user);
        }
    }

    @Override
    public List<User> findAll() {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findAll();
        }
    }

    @Override
    public BigDecimal getUserBalance(User user) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.getUserBalance(user);
        }
    }

    @Override
    public BigDecimal balanceReplenishment(BigDecimal amount, User user) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.balanceReplenishment(amount, user);
        }
    }
}
