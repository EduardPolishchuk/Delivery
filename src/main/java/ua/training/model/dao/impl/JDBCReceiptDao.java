package ua.training.model.dao.impl;

import ua.training.model.dao.ReceiptDao;
import ua.training.model.entity.Receipt;

import java.util.List;
import java.util.Optional;

public class JDBCReceiptDao implements ReceiptDao {

    @Override
    public Optional<Receipt> findById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean create(Receipt entity) {
        return false;
    }

    @Override
    public boolean update(Receipt entity) {
        return false;
    }

    @Override
    public List<Receipt> findAll() {
        return null;
    }

    @Override
    public void close() {

    }
}
