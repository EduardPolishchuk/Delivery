package ua.training.model.dao.impl;

import ua.training.model.dao.ReceiptDao;

import java.util.List;
import java.util.Optional;

public class JDBCReceiptDao implements ReceiptDao {
    @Override
    public Optional findById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean create(Object entity) {
        return false;
    }

    @Override
    public boolean update(Object entity) {
        return false;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void close() {

    }
}
