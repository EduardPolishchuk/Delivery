package ua.training.model.service.impl;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ReceiptDao;
import ua.training.model.entity.Receipt;
import ua.training.model.entity.User;
import ua.training.model.service.ReceiptService;

import java.util.List;
import java.util.Optional;

public class ReceiptServiceImpl implements ReceiptService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public Optional<Receipt> findById(long id) {
        try(ReceiptDao receiptDao = daoFactory.createReceiptDao()){
            return receiptDao.findById(id);
        }
    }

    @Override
    public boolean create(Receipt receipt) {
        try(ReceiptDao receiptDao = daoFactory.createReceiptDao()){
            return receiptDao.create(receipt);
        }
    }

    @Override
    public boolean update(Receipt receipt) {
        try (ReceiptDao receiptDao = daoFactory.createReceiptDao()) {
            return receiptDao.update(receipt);
        }
    }

    @Override
    public List<Receipt> findUserReceipts(User user) {
        try(ReceiptDao receiptDao = daoFactory.createReceiptDao()){
            return receiptDao.findUserReceipts(user);
        }
    }

    @Override
    public List<Receipt> findAll() {
        try (ReceiptDao receiptDao = daoFactory.createReceiptDao()) {
            return receiptDao.findAll();
        }
    }
}
