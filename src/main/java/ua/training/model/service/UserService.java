package ua.training.model.service;

import ua.training.model.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findIfValid(String login, String password);

    boolean createUser(User user);
}
