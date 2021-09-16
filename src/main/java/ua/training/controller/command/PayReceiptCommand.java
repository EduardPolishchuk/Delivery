package ua.training.controller.command;

import ua.training.model.entity.Receipt;
import ua.training.model.service.impl.ReceiptServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class PayReceiptCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return  "redirect:/user/userReceipts";
    }
}
