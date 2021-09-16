package ua.training.controller.command;

import ua.training.model.entity.Receipt;
import ua.training.model.service.ReceiptService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserReceiptsCommand implements Command {

    private ReceiptService receiptService;

    public UserReceiptsCommand(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Receipt> list = receiptService.findAll();
        request.getSession().setAttribute("userReceipts", list);
        return "/user/userReceipts.jsp";
    }
}
