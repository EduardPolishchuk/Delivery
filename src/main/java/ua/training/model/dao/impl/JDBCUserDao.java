package ua.training.model.dao.impl;

import ua.training.model.dao.UserDao;

import java.sql.Connection;

public class JDBCUserDao implements UserDao {
    private final Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }
}
