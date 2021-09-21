package ua.training.model.dao.mapper;


import ua.training.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ObjectMapper<User> {


    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong("user_id"))
                .login(rs.getString("login"))
                .role(User.Role.valueOf(rs.getString("role_name").toUpperCase()))
                .balance(rs.getBigDecimal("balance"))
                .email(rs.getString("email"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .build();
    }
}
