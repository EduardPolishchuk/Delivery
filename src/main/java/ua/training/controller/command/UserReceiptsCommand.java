package ua.training.controller.command;

import ua.training.model.service.ReceiptService;

import javax.servlet.http.HttpServletRequest;

public class UserReceiptsCommand implements Command {

    private ReceiptService receiptService;

    public UserReceiptsCommand(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
