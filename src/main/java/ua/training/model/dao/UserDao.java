package ua.training.model.dao;

import ua.training.model.entity.User;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    Optional<User> findIfValid(String login, String password);

    BigDecimal getUserBalance(User user);

    BigDecimal balanceReplenishment(BigDecimal amount, User user);
}
