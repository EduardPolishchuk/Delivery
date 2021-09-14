package ua.training.model.service.impl;

import ua.training.model.entity.Receipt;
import ua.training.model.service.ReceiptService;

import java.util.List;
import java.util.Optional;

public class ReceiptServiceImpl implements ReceiptService {
    @Override
    public Optional<Receipt> findById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean create(Receipt receipt) {
        return false;
    }

    @Override
    public boolean update(Receipt receipt) {
        return false;
    }

    @Override
    public List<Receipt> findAll() {
        return null;
    }
}
