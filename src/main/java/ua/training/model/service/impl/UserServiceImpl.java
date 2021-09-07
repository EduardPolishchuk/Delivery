package ua.training.model.service.impl;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public Optional<User> findIfValid(String login, String password) {
        try(UserDao userDao = daoFactory.createUserDao()){
            return userDao.getUser(login, password);
        }
    }

    @Override
    public boolean createUser(User user) {
        try(UserDao userDao = daoFactory.createUserDao()){
            return userDao.create(user);
        }
    }
}
