package ua.training.model.dao;

import ua.training.model.entity.Receipt;
import ua.training.model.entity.User;

import java.util.List;

public interface ReceiptDao extends GenericDao<Receipt> {

    List<Receipt> findUserReceipts(User user);

    boolean userPaysReceipt(User user, Receipt receipt);
}
