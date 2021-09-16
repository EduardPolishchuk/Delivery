package ua.training.model.service;

import ua.training.model.entity.Receipt;
import ua.training.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface ReceiptService {
    Optional<Receipt> findById(long id);

    boolean create(Receipt receipt);

    boolean update(Receipt receipt);

    List<Receipt> findUserReceipts(User user, boolean paid);

    boolean userPaysReceipt(User user, Receipt receipt);

    List<Receipt> findAll();
}
