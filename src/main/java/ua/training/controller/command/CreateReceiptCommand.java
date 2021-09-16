package ua.training.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.service.ReceiptService;

import javax.servlet.http.HttpServletRequest;

public class CreateReceiptCommand implements Command{

    private static final Logger logger = LogManager.getLogger(MakeOrderCommand.class);
    private ReceiptService receiptService;

    public CreateReceiptCommand(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
